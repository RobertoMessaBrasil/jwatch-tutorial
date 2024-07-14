package com.jwatch.tutorial.security;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.tutorial.entity.user.UserEntity;
import com.jwatch.tutorial.entity.user.UserRoleEnum;
import com.jwatch.tutorial.entity.user.event.UserValidationEvent;
import com.jwatch.tutorial.listener.SecurityListener;
import com.jwatch.tutorial.listener.ValidationListener;
import com.jwatch.tutorial.security.event.InvalidRoleEvent;

import java.util.List;

public class SecurityApp {
    public static void main(String[] args) {

        ValidationListener validationListener = new ValidationListener();
        validationListener.setEventsOfInterest(List.of(UserValidationEvent.class));

        SecurityListener securityListener = new SecurityListener();
        securityListener.setEventsOfInterest(List.of(InvalidRoleEvent.class));

        EventObserver eventObserver = new EventObserver()
                .subscribe(validationListener)
                .subscribe(securityListener);

        UserEntity adminUser = new UserEntity().setRoleEnum(UserRoleEnum.REGULAR);
        UserEntity newUser = new UserEntity()
                .setName("Roberto Messa")
                .setEmail("myemail@myhost.com");

        try {
            UserEntity createdUser = UserService.createUser(adminUser, newUser, eventObserver);
            System.out.println(createdUser);
        } catch (InterruptException e) {
            System.out.println(eventObserver.getInterruptEvent());
        }
    }
}
