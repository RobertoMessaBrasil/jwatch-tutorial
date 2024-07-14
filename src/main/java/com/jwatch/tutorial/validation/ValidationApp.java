package com.jwatch.tutorial.validation;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.tutorial.entity.user.UserEntity;
import com.jwatch.tutorial.entity.user.event.UserValidationEvent;
import com.jwatch.tutorial.listener.ValidationListener;

import java.util.List;

public class ValidationApp {
    public static void main(String[] args) {

        ValidationListener validationListener = new ValidationListener();
        validationListener.setEventsOfInterest(List.of(UserValidationEvent.class));

        EventObserver eventObserver = new EventObserver();
        eventObserver.subscribe(validationListener);

        UserEntity user = new UserEntity();
        user.setName("Roberto Messa");
        user.setEmail(null);

        ValidationApp validationApp = new ValidationApp();

        try {
            validationApp.createUser(user, eventObserver);
        } catch (InterruptException e) {
            System.out.println(eventObserver.getInterruptEvent());
        }

    }

    void createUser(UserEntity user, EventObserver eventObserver) throws InterruptException {
        user.validate(eventObserver);
        System.out.println("User created:");
        System.out.println(user);
    }

}

