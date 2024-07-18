package io.github.robertomessabrasil.jwatch.tutorial.security;

import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserEntity;
import io.github.robertomessabrasil.jwatch.tutorial.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.jwatch.tutorial.security.event.InvalidRoleEvent;

import java.util.List;

public class UserService {

    public static UserEntity createUser(UserEntity adminUserEntity, UserEntity userEntity, EventObserver eventObserver) throws InterruptException {
        checkRole(adminUserEntity, List.of(UserRoleEnum.ADMIN), eventObserver);
        userEntity.validate(eventObserver);
        return new UserEntity()
                .setName(userEntity.getName())
                .setEmail(userEntity.getEmail())
                .setRoleEnum(UserRoleEnum.REGULAR);
    }

    public static void checkRole(UserEntity user, List<UserRoleEnum> roles, EventObserver eventObserver) throws InterruptException {
        for (UserRoleEnum role : roles) {
            if (user.getRoleEnum().equals(role)) {
                return;
            }
        }
        eventObserver.notify(new InvalidRoleEvent(user.getRoleEnum()));
    }

}
