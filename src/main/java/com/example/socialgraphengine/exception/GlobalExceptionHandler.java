package com.example.socialgraphengine.exception;

import com.example.socialgraphengine.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 * Maps custom exceptions to appropriate HTTP status codes and response
 * messages.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /**
     * Handles generic BusinessException - returns HTTP 500 Internal Server Error
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e , HttpServletRequest request) {
        log.error("Business exception: {}", e.getMessage(), e);
        ErrorResponse response = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode().name())
                .path(request.getRequestURI())
                .httpStatus(e.getStatus().value())
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Handles validation errors from @Valid annotations - returns HTTP 400 Bad
     * Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e , HttpServletRequest request) {

        log.error("MethodArgumentNotValidException : {}",e.getMessage());

        Map<String , String> fieldsErrors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField
                                ,fe -> fe.getDefaultMessage() != null ?  fe.getDefaultMessage():"invalid value",
                                (msg1 , msg2) -> msg1
                                )

                        );

        ErrorResponse response = ErrorResponse.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .extra(fieldsErrors)
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * Handles all other exceptions - returns HTTP 500 Internal Server Error
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e , HttpServletRequest request) {
        log.error("Unexpected exception occurred", e);

        ErrorResponse response = ErrorResponse.builder()
                .message(e.getMessage())
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.ok(response);
    }
}
