package com.example.socialgraphengine.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a requested resource is not found.
 * Typically results in HTTP 404 status code.
 */
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.NOT_FOUND , errorCode);
    }
    public ResourceNotFoundException( ErrorCode errorCode) {
        this(errorCode.getMessage() , errorCode);
    }


}
