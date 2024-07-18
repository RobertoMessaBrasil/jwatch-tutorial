package io.github.robertomessabrasil.jwatch.tutorial.security.event;

import com.jwatch.observer.listener.Event;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserRoleEnum;

public class InvalidRoleEvent extends Event {

    final private UserRoleEnum roleEnum;

    public InvalidRoleEvent(UserRoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public UserRoleEnum getRoleEnum() {
        return roleEnum;
    }

    public String toString() {
        return "Role not authorized: " + this.roleEnum;
    }

}
