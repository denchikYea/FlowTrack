package by.kolp.api.controller;

import by.kolp.api.factories.UserRegistrationDtoFactory;
import by.kolp.api.model.dto.AckDTO;
import by.kolp.api.model.dto.UserCreatingRequestDTO;
import by.kolp.api.model.dto.UserRegistrationDTO;
import by.kolp.api.model.entity.User;
import by.kolp.api.model.exceptions.BadRequestException;
import by.kolp.api.model.exceptions.NotFoundException;
import by.kolp.api.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
public class UserController {


    final UserRepository userRepository;

    final UserRegistrationDtoFactory userRegistrationDtoFactory;

    public static final String FETCH_USERS = "/api/user";
    public static final String DELETE_USER = "/api/user/{user_id}";
    public static final String CREATE_OR_UPDATE_USER = "/api/user";

    @PutMapping(CREATE_OR_UPDATE_USER)
    public UserRegistrationDTO createOrUpdateUser(@RequestParam(value = "user_id",required = false) Optional<Long> optionalUserId,
                                                  @RequestParam(value = "user_name",required = false) Optional<String> optionalUserName)
    {
        User user = optionalUserId
                .map(this::getUserOrThrowException)
                .orElseGet(() -> User.builder().build());

        if(!optionalUserId.isPresent() && !optionalUserName.isPresent()) {
            throw new BadRequestException("Username cannot be empty!");
        }

        userRepository.findByUsername(user.getUsername())
                .filter(anotherUser -> !Objects.equals(anotherUser.getId(), optionalUserId))
                .ifPresent(anotherUser -> {
                    throw new BadRequestException(String.format("User \"%s\" already exists.", optionalUserName));
                });

        user.setUsername(user.getUsername());

        final User saveUser = userRepository.saveAndFlush(user);

        return userRegistrationDtoFactory.makeUserRegistrationDto(saveUser);
    }

    @DeleteMapping(value = DELETE_USER)
    public AckDTO deleteUser(@PathVariable("user_id") Long userId) {
        User user = getUserOrThrowException(userId);


        userRepository.deleteUserBy(user.getId());
        return new AckDTO("User successfully deleted", true);
    }

    private User getUserOrThrowException(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new NotFoundException(String
                                .format("User \"%s%\" doesn't exist", userId)));
        return user;
    }

    @GetMapping(FETCH_USERS)
    public List<UserRegistrationDTO> fetchUsers(@RequestParam(value = "prefix_name", required = false) Optional<String> optionalPrefixName) {

        optionalPrefixName = optionalPrefixName.filter(prefixName -> !prefixName.trim().isEmpty());


        Stream<User> users = optionalPrefixName.stream()
                .map(userRepository::streamAllByUsernameStartingWithIgnoreCase)
                .findAny().orElseGet(userRepository::streamAll);


        return users
                .map(userRegistrationDtoFactory::makeUserRegistrationDto)
                .collect(Collectors.toList());
    }

}