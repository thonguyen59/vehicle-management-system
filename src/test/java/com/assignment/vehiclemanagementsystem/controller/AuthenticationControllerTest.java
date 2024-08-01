package com.assignment.vehiclemanagementsystem.controller;

import com.assignment.vehiclemanagementsystem.payload.request.AuthenticationRequest;
import com.assignment.vehiclemanagementsystem.payload.request.RegisterRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.AuthenticationResponse;
import com.assignment.vehiclemanagementsystem.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        RegisterRequest request = new RegisterRequest();
        AuthenticationResponse response = new AuthenticationResponse();
        when(authenticationService.register(request)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authenticationController.register(request);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testAuthenticate() {
        AuthenticationRequest request = new AuthenticationRequest();
        AuthenticationResponse response = new AuthenticationResponse();
        when(authenticationService.authenticate(request)).thenReturn(response);

        ResponseEntity<AuthenticationResponse> result = authenticationController.authenticate(request);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
