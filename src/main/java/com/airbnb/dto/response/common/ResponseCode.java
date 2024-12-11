package com.airbnb.dto.response.common;

public enum ResponseCode {

    // Success codes
    SUCCESS(200, "Success"),
    CREATED(201, "Created"),

    // Client error codes
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),

    // Server error codes
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // Custom business codes
    VALIDATION_ERROR(1001, "Validation Error"),
    BUSINESS_ERROR(1002, "Business Error");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}