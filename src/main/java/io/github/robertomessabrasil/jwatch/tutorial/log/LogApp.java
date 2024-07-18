package io.github.robertomessabrasil.jwatch.tutorial.log;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.observer.listener.EventListener;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserEntity;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.jwatch.tutorial.listener.LogListener;
import io.github.robertomessabrasil.jwatch.tutorial.listener.SecurityListener;
import io.github.robertomessabrasil.jwatch.tutorial.listener.ValidationListener;
import io.github.robertomessabrasil.jwatch.tutorial.security.UserService;
import io.github.robertomessabrasil.jwatch.tutorial.security.event.InvalidRoleEvent;

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
