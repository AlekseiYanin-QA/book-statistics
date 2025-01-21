package com.edu.bookstatistics.repositories;

import com.edu.bookstatistics.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Находит пользователя по Google ID.
     *
     * @param googleId Google ID пользователя.
     * @return Optional с пользователем, если найден.
     */
    Optional<User> findByGoogleId(String googleId);

    /**
     * Находит пользователя по email.
     *
     * @param email Email пользователя.
     * @return Optional с пользователем, если найден.
     */
    Optional<User> findByEmail(String email);
}