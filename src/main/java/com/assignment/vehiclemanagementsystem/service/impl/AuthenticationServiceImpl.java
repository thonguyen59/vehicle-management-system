package com.assignment.vehiclemanagementsystem.service.impl;

import com.assignment.vehiclemanagementsystem.config.JwtService;
import com.assignment.vehiclemanagementsystem.constant.Role;
import com.assignment.vehiclemanagementsystem.entity.User;
import com.assignment.vehiclemanagementsystem.payload.request.AuthenticationRequest;
import com.assignment.vehiclemanagementsystem.payload.request.RegisterRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.AuthenticationResponse;
import com.assignment.vehiclemanagementsystem.repository.UserRepository;
import com.assignment.vehiclemanagementsystem.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
