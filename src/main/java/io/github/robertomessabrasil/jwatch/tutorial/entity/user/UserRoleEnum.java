package io.github.robertomessabrasil.jwatch.tutorial.entity.user;

public enum UserRoleEnum {
    ADMIN(0), REGULAR(1);

    private final int code;

    UserRoleEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
