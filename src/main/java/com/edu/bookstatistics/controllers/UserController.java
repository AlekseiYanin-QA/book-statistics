package com.edu.bookstatistics.controllers;

import com.edu.bookstatistics.entities.User;
import com.edu.bookstatistics.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "Operations related to user management")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    @Operation(summary = "Get current user info", security = @SecurityRequirement(name = "bearerAuth"))
    public Map<String, Object> getCurrentUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        Map<String, Object> userInfo = new HashMap<>();
        if (oidcUser != null) {
            userInfo.put("id", oidcUser.getSubject());
            userInfo.put("name", oidcUser.getFullName());
            userInfo.put("email", oidcUser.getEmail());
            userInfo.put("picture", oidcUser.getPicture());
        }
        return userInfo;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    @Operation(summary = "Get all users", security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}