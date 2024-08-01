package com.assignment.vehiclemanagementsystem.controller;

import com.assignment.vehiclemanagementsystem.service.AuthenticationService;
import com.assignment.vehiclemanagementsystem.payload.request.AuthenticationRequest;
import com.assignment.vehiclemanagementsystem.payload.request.RegisterRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {

        return ResponseEntity.ok(service.authenticate(request));
    }

}