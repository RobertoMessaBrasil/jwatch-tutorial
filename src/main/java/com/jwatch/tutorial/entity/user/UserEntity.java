package com.jwatch.tutorial.entity.user;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.tutorial.entity.user.event.UserValidationCode;
import com.jwatch.tutorial.entity.user.event.UserValidationEvent;

public class UserEntity {
    private String name;
    private String email;
    private UserRoleEnum roleEnum;

    public void validate(EventObserver eventObserver) throws InterruptException {

        if ((this.name == null) || (this.name.length() < 3)) {
            UserValidationEvent userValidationEvent = new UserValidationEvent();
            userValidationEvent.setUser(this);
            userValidationEvent.setCode(UserValidationCode.INVALID_NAME);
            eventObserver.notify(userValidationEvent);
        }

        if ((this.email == null) || (this.email.length() < 3)) {
            UserValidationEvent userValidationEvent = new UserValidationEvent();
            userValidationEvent.setUser(this);
            userValidationEvent.setCode(UserValidationCode.INVALID_EMAIL);
            eventObserver.notify(userValidationEvent);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + roleEnum +
                '}';
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public UserEntity setRoleEnum(UserRoleEnum roleEnum) {
        this.roleEnum = roleEnum;
        return this;
    }
}
