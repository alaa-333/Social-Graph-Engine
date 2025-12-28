package com.example.socialgraphengine.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception thrown when a user attempts an unauthorized action.
 * Typically results in HTTP 401 Unauthorized or 403 Forbidden status code.
 */
public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(String message,HttpStatus status, ErrorCode errorCode) {
        super(message, status , errorCode);
    }
    public UnauthorizedException( HttpStatus status , ErrorCode errorCode) {
        this(errorCode.getMessage(),status , errorCode);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
