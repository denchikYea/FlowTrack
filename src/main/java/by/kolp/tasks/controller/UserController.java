package by.kolp.tasks.controller;

import by.kolp.tasks.factories.UserRegistrationDtoFactory;
import by.kolp.tasks.model.dto.UserCreatingRequestDTO;
import by.kolp.tasks.model.dto.UserRegistrationDTO;
import by.kolp.tasks.model.entity.User;
import by.kolp.tasks.model.exceptions.BadRequestException;
import by.kolp.tasks.model.exceptions.NotFoundException;
import by.kolp.tasks.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Objects;

@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE )
@RestController
public class UserController {


    final UserRepository userRepository;


    final UserRegistrationDtoFactory userRegistrationDtoFactory;

    public static final String CREATE_USER = "/api/user/create";
    public static final String FETCH_USER = "/api/user/create";
    public static final String EDIT_USER = "/api/user/{user_id}";


    @PostMapping(CREATE_USER)
    public UserRegistrationDTO register(@RequestBody UserCreatingRequestDTO request) {

        if(request.getUsername().trim().isEmpty() || request.getPassword().trim().isEmpty()) {
            throw new BadRequestException("Username or password cannot be empty");
        }

        userRepository
                .findByUsername(request.getUsername())
                .ifPresent(
                        user -> {
                            throw new BadRequestException(String.format("User \"%s\" already exists.", request.getUsername()));
                        });

        User newUser = userRepository.saveAndFlush(
                User.builder()
                        .username(request.getUsername())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        return userRegistrationDtoFactory.makeUserRegistrationDto(newUser);
    }

    @PatchMapping(EDIT_USER)
    public UserRegistrationDTO editUsername(@PathVariable("user_id") Long userId,
                                        @RequestBody UserCreatingRequestDTO request) {


        if(request.getUsername().trim().isEmpty()) {
            throw new BadRequestException("Username cannot be empty");
        }

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new NotFoundException(String.format("User \"%s%\" doesn't exist", userId)));

        userRepository.findByUsername(user.getUsername())
                .filter(anotherUser -> !Objects.equals(anotherUser.getId(), userId))
                .ifPresent(anotherUser -> {
                        throw new BadRequestException(String.format("User \"%s\" already exists.", request.getUsername()));


                });
        user.setUsername(request.getUsername());

        user = userRepository.saveAndFlush(user);

        return userRegistrationDtoFactory.makeUserRegistrationDto(user);
    }
}
