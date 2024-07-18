package io.github.robertomessabrasil.jwatch.tutorial.security;

import com.jwatch.exception.InterruptException;
import com.jwatch.observer.EventObserver;
import com.jwatch.observer.listener.EventListener;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserEntity;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.jwatch.tutorial.listener.SecurityListener;
import io.github.robertomessabrasil.jwatch.tutorial.listener.ValidationListener;
import io.github.robertomessabrasil.jwatch.tutorial.security.event.InvalidRoleEvent;

public class SecurityApp {
    public static void main(String[] args) {

        EventListener validationListener = new ValidationListener().addEvent(UserValidationEvent.class);
        EventListener securityListener = new SecurityListener().addEvent(InvalidRoleEvent.class);

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
