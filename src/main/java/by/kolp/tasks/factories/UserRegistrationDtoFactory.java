package by.kolp.tasks.factories;

import by.kolp.tasks.model.dto.UserRegistrationDTO;
import by.kolp.tasks.model.entity.User;

public class UserRegistrationDtoFactory {
    public UserRegistrationDTO makeUserRegistrationDto(User user) {
        return UserRegistrationDTO.builder().username(user.getUsername()).email(user.getEmail()).build();
    }

    public UserRegistrationDTO makeUserRegistrationDto(String username) {
        return UserRegistrationDTO.builder()
                .username(username)
                        .build();
    }
}
