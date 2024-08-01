package com.assignment.vehiclemanagementsystem.payload.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}
