package com.airbnb.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class DuplicateEntityException extends RuntimeException {
    private final List<String> errors;

    public DuplicateEntityException(List<String> errors) {
        this.errors = errors;
    }
}