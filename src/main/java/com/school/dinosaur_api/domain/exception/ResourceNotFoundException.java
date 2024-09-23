package com.school.dinosaur_api.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
