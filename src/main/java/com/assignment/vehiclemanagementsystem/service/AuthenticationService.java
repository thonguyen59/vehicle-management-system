
package com.assignment.vehiclemanagementsystem.service;



import com.assignment.vehiclemanagementsystem.payload.request.AuthenticationRequest;
import com.assignment.vehiclemanagementsystem.payload.request.RegisterRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
