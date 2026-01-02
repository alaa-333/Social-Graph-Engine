package com.example.socialgraphengine.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Validation Errors (VAL-XXX)
    VALIDATION_FAILED("VAL-001", "Request validation failed", HttpStatus.BAD_REQUEST),
    DUPLICATE_ENTRY("VAL-020", "Duplicate entry found", HttpStatus.BAD_REQUEST),


    // Authentication & Authorization Errors (AUTH-XXX)
    UNAUTHORIZED_ACCESS("AUTH-001", "Unauthorized access", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("AUTH-002", "Forbidden", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS("AUTH-003", "Invalid email or password", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("AUTH-004", "Authentication token expired", HttpStatus.UNAUTHORIZED),
    TOKEN_INVALID("AUTH-005", "Invalid authentication token", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED("AUTH-006", "Account is locked", HttpStatus.UNAUTHORIZED),


    // System & Infrastructure Errors (SYS-XXX)
    INTERNAL_SERVER_ERROR("SYS-001", "Unexpected server error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("SYS-002", "Invalid request data", HttpStatus.BAD_REQUEST),
    DATABASE_ERROR("SYS-003", "Database error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE("SYS-004", "Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    REQUEST_TIMEOUT("SYS-005", "Request timeout", HttpStatus.REQUEST_TIMEOUT),
    DATA_INTEGRITY_VIOLATION("SYS-006", "Data integrity violation", HttpStatus.CONFLICT),
    DATABASE_UNAVAILABLE("SYS-003", "Database connection failed", HttpStatus.SERVICE_UNAVAILABLE),
    EXTERNAL_SERVICE_ERROR("SYS-004", "External service unavailable", HttpStatus.SERVICE_UNAVAILABLE),

    // User Errors
    USER_NOT_FOUND("USER-001", "User not found" , HttpStatus.NOT_FOUND);


    private final String code;
    private final String message;
    private final HttpStatus Status;

}
