package by.kolp.tasks.repository.interfaces;

import by.kolp.tasks.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}