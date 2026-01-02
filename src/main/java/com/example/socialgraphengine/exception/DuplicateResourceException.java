package com.example.socialgraphengine.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when attempting to create a resource that already exists.
 * Typically results in HTTP 409 Conflict status code.
 */
public class DuplicateResourceException extends BusinessException {


    public DuplicateResourceException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.CONFLICT , errorCode);
    }
    public DuplicateResourceException(ErrorCode errorCode) {
        this(errorCode.getMessage() , errorCode);
    }

}
