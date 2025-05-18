package by.kolp.tasks.controller;

import by.kolp.tasks.model.dto.UserRegistrationDTO;
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
    public UserRegistrationDTO register(@RequestBody String username) {

      userRepository
              .findByUsername(username)
              .ifPresent ((user) -> {

                throw new BadRequestException(String.format("Username \"%s\" already exists.", username));
              });

        return register(username);
    }
}
