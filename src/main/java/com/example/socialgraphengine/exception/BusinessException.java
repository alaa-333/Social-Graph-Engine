package com.example.socialgraphengine.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base exception class for all business logic exceptions.
 * Uses message keys for internationalization support.
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String message;
    private final HttpStatus status;
    private final ErrorCode errorCode;


    public BusinessException(String message, HttpStatus status , ErrorCode errorCode) {
        super(message != null ? message:errorCode.getMessage());
        this.message = message;
        this.status = status;
        this.errorCode = errorCode;
    }


    public BusinessException( HttpStatus status , ErrorCode errorCode) {
        this(errorCode.getMessage() , status, errorCode);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

}
