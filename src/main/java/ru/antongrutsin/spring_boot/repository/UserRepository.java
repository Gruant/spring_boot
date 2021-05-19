package ru.antongrutsin.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.spring_boot.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
