package com.assignment.vehiclemanagementsystem.payload.request;

import com.assignment.vehiclemanagementsystem.constant.FuelType;
import com.assignment.vehiclemanagementsystem.constant.VehicleType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleRequest {
    private String make;
    private String model;
    private int year;
    private VehicleType type;
    private String licensePlate;
    private String identificationNumber;
    private String color;
    private FuelType fuelType;
    private String status;
    private String ownerUsername;
    private Boolean enabled;
}
