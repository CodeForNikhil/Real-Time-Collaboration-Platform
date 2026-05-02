package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateUserException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;
import com.example.demo.service.AuthService;
import com.example.demo.service.PresenceService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private  PasswordEncoder passwordEncoder;
	@Autowired
	private  AuthenticationManager authenticationManager;
	@Autowired
	private  JwtService jwtService;
	@Autowired
	private  PresenceService presenceService;


    @Override
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUserException("Username already exists");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setActive(true);

        User savedUser = userRepository.save(user);
                
        userRepository.save(user);
        String token = jwtService.generateToken(savedUser.getUsername());
        presenceService.markUserOnline(user.getId());
        return new LoginResponse(token, user.getId(), user.getFullName(), user.getRole());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user.getUsername());
        presenceService.markUserOnline(user.getId());
        return new LoginResponse(token, user.getId(), user.getFullName(), user.getRole());
    }

    @Override
    public void logoutCurrentUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            return;
        }

        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("User not found for logout")
        );

        presenceService.clearUserSession(user.getId());
        SecurityContextHolder.clearContext();
    }


}
