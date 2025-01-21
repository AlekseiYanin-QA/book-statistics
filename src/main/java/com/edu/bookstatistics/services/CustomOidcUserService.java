package com.edu.bookstatistics.services;

import com.edu.bookstatistics.entities.User;
import com.edu.bookstatistics.repositories.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    private final UserRepository userRepository;

    public CustomOidcUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        // Извлечение данных из OIDC пользователя
        String googleId = oidcUser.getSubject();
        String email = oidcUser.getEmail();
        String name = oidcUser.getFullName();
        String picture = oidcUser.getPicture();

        // Поиск пользователя в базе данных по Google ID
        Optional<User> existingUser = userRepository.findByGoogleId(googleId);

        // Если пользователь не найден, создаем нового
        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setGoogleId(googleId);
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setPicture(picture);
            userRepository.save(newUser);
        }

        return oidcUser;
    }
}