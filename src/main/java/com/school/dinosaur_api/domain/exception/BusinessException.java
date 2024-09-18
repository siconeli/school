package com.school.dinosaur_api.domain.exception;

public class BusinessException extends  RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
