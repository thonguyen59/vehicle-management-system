package com.assignment.vehiclemanagementsystem.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MaintenanceRecordRequest {
    @NotNull
    private Long vehicleId;

    @NotNull
    private LocalDate serviceDate;

    private String description;

    @NotNull
    private Double cost;
}
