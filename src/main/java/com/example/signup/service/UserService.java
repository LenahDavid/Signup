package com.example.signup.service;

import com.example.signup.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);
    UserDetailsService userDetailsService();
}
