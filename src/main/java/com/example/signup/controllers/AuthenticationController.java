package com.example.signup.controllers;

import com.example.signup.dto.SigninRequest;
import com.example.signup.dto.SignupRequest;
import com.example.signup.entities.User;
import com.example.signup.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthenticationController {
    private  final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<User> signup(@RequestBody SignupRequest signupRequest){
        try {
            User user = authenticationService.signup(signupRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Operation(
            description = "Signing in a User",
            summary = "Signing in of users"

    )
//    signing in a user
    @PostMapping("/api/v1/auth/signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        try {
            Optional<User> user = authenticationService.signin(signinRequest);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
