package com.example.socialgraphengine.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when business validation fails.
 * Typically results in HTTP 400 Bad Request status code.
 */
public class ValidationException extends BusinessException {


    public ValidationException(String message, ErrorCode errorCode) {
        super(message, HttpStatus.BAD_REQUEST , errorCode);
    }
    public ValidationException(ErrorCode errorCode) {
        this(errorCode.getMessage() , errorCode);
    }


}
