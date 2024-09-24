package com.school.dinosaur_api.api.exceptionhandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.school.dinosaur_api.domain.exception.BusinessException;
import com.school.dinosaur_api.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    // SOBRESCREVENDO MÉTODO QUE MANIPULA EXCEPTION DE ARGUMENTO NÃO VÁLIDO DO JAKARTA BEAN VALIDATION - IMPLEMENTANDO PROBLEMDETAIL - RFC 7807
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create("https://api.dinosaur/errors/validation-error"));
        problemDetail.setTitle("One or more fields are invalid");

        Map<String, String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField(), objectError -> messageSource.getMessage(objectError, Locale.US)));

        problemDetail.setProperty("fields", fields);

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    // SOBRESCREVENDO MÉTODO QUE MANIPULA EXCEPTION DE ERROS NA DESSERIALIZAÇÃO DO JSON FORNECIDO NO CORPO DA REQUISIÇÃO
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setType(URI.create("https://api.dinosaur/errors/jackson-error"));
        problemDetail.setTitle("Error deserializing JSON provided in the request body");

        if (ex.getCause() instanceof JsonMappingException jsonMappingException) {
            String errorField = jsonMappingException.getPath()
                    .stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .reduce((first, second) -> first + "." + second)
                    .orElse("Unknown field");

            problemDetail.setDetail("Field with error is: " + errorField);
        }

        return handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://api.dinosaur/errors/business-error"));
        problemDetail.setTitle("Business rule error");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFoundException(ResourceNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://api.dinosaur/errors/resource-not-found"));
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setType(URI.create("https://api.dinosaur/errors/data-integrity-violation"));
        problemDetail.setTitle("Data integrity violation");
        problemDetail.setDetail(e.getMessage());

        return problemDetail;
    }
}
