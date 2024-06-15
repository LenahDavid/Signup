package com.example.signup.service;

import com.example.signup.dto.SigninRequest;
import com.example.signup.dto.SignupRequest;
import com.example.signup.entities.User;
import com.example.signup.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public Optional<User> signin(SigninRequest signinRequest) {
        Optional<User> user = userRepository.findByEmail(signinRequest.getEmail());
        if (user != null && passwordEncoder.matches(signinRequest.getPassword(), user.get().getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid credentials for email: " + signinRequest.getEmail());
        }
    }
}