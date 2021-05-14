package ru.antongrutsin.spring_boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antongrutsin.spring_boot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
