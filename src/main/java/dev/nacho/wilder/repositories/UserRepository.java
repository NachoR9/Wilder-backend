package dev.nacho.wilder.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.nacho.wilder.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);

}
