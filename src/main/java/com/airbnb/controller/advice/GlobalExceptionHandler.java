package com.airbnb.controller.advice;

import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.exception.DuplicateEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Map<String, List<String>>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.computeIfAbsent(error.getField(), key -> new ArrayList<>()).add(error.getDefaultMessage()));

        CommonResponse<Map<String, List<String>>> response = new CommonResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation error",
                fieldErrors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CommonResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        CommonResponse<String> response = new CommonResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Illegal argument",
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<String>> handleGlobalException(Exception ex) {
        CommonResponse<String> response = new CommonResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error",
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<CommonResponse<List<String>>> handleDuplicateEntityException(DuplicateEntityException ex) {
        CommonResponse<List<String>> response = new CommonResponse<>(
                HttpStatus.CONFLICT.value(),
                "Already exists",
                ex.getErrors()
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}