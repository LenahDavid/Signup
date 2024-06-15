package com.example.signup.service;

import com.example.signup.dto.SigninRequest;
import com.example.signup.dto.SignupRequest;
import com.example.signup.entities.User;

import java.util.Optional;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest) ;
    Optional<User> signin(SigninRequest signinRequest) ;

}

