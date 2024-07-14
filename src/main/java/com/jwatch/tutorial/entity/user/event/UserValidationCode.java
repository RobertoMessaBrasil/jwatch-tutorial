package com.jwatch.tutorial.entity.user.event;

public enum UserValidationCode {
    INVALID_NAME(0), INVALID_EMAIL(1);

    private final int code;

    private UserValidationCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
