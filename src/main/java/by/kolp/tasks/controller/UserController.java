package by.kolp.tasks.controller;

import by.kolp.tasks.model.dto.UserRegistrationDTO;
import by.kolp.tasks.model.entity.User;
import by.kolp.tasks.model.exceptions.BadRequestException;
import by.kolp.tasks.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class UserController {

  UserRepository userRepository;

    public static final String CREATE_USER = "/api/user/create";


    @PostMapping(CREATE_USER)
    public UserRegistrationDTO register(@RequestBody String username, String email, String password) {

      userRepository
              .findByUsername(username)
              .ifPresentOrElse (
                      (user) -> {

                throw new BadRequestException(String.format("Username \"%s\" already exists.", username));
              },() -> {
                          User newUser = new User();
                          newUser.setUsername(username);
                          newUser.setEmail(username);
                          newUser.setPassword(password);
                          userRepository.save(newUser);
                      });

        return new UserRegistrationDTO(username, email, password);
    }
}
