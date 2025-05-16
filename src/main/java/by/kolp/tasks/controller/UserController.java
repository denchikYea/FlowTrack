package by.kolp.tasks.controller;

import by.kolp.tasks.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class UserController {

    UserRepository userRepository;

    //UserDtoFactory userDtoFactory;

    //todo create ifPresent


}
