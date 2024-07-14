package com.jwatch.tutorial.log;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.observer.listener.EventListener;
import com.jwatch.tutorial.entity.user.UserEntity;
import com.jwatch.tutorial.entity.user.UserRoleEnum;
import com.jwatch.tutorial.entity.user.event.UserValidationEvent;
import com.jwatch.tutorial.listener.LogListener;
import com.jwatch.tutorial.listener.SecurityListener;
import com.jwatch.tutorial.listener.ValidationListener;
import com.jwatch.tutorial.security.UserService;
import com.jwatch.tutorial.security.event.InvalidRoleEvent;

public class LogApp {
    public static void main(String[] args) throws InterruptException {


        EventListener validationListener = new ValidationListener().addEvent(UserValidationEvent.class);
        EventListener securityListener = new SecurityListener().addEvent(InvalidRoleEvent.class);
        EventListener logListener = new LogListener()
                .addEvent(LogEvent.class)
                .addEvent(UserValidationEvent.class)
                .addEvent(InvalidRoleEvent.class);

        EventObserver eventObserver = new EventObserver()
                .subscribe(validationListener)
                .subscribe(securityListener)
                .subscribe(logListener);

        eventObserver.notify(new LogEvent().setMessage("flow started"));

        UserEntity adminUser = new UserEntity().setRoleEnum(UserRoleEnum.ADMIN);

        UserEntity newUser = new UserEntity()
                .setName("Roberto Messa")
                .setEmail("account@host.com");

        try {
            UserEntity createdUser = UserService.createUser(adminUser, newUser, eventObserver);
            eventObserver.notify(new LogEvent().setMessage("created user" + createdUser.toString()));
        } catch (InterruptException e) {
            eventObserver.reset();
        }

        eventObserver.notify(new LogEvent().setMessage("flow finished"));

    }
}
