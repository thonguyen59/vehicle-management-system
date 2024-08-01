package com.assignment.vehiclemanagementsystem.service;

import com.assignment.vehiclemanagementsystem.entity.MaintenanceRecord;
import com.assignment.vehiclemanagementsystem.entity.Vehicle;
import com.assignment.vehiclemanagementsystem.payload.UserPrincipal;
import com.assignment.vehiclemanagementsystem.payload.request.MaintenanceRecordRequest;
import com.assignment.vehiclemanagementsystem.payload.request.VehicleRequest;
import org.apache.coyote.BadRequestException;


import java.awt.print.Pageable;
import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles(UserPrincipal role);

    Vehicle create(VehicleRequest request) throws BadRequestException;

    Vehicle update(Long id, VehicleRequest request) throws BadRequestException;

}
