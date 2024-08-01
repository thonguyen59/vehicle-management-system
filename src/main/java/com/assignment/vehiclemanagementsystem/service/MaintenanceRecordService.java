package com.assignment.vehiclemanagementsystem.service;

import com.assignment.vehiclemanagementsystem.entity.MaintenanceRecord;
import com.assignment.vehiclemanagementsystem.payload.request.MaintenanceRecordRequest;
import org.apache.coyote.BadRequestException;

import java.util.List;


public interface MaintenanceRecordService {
    List<MaintenanceRecord> getAllByVehicleId(Long id);

    MaintenanceRecord create(MaintenanceRecordRequest request) throws BadRequestException;

    MaintenanceRecord update(Long id, MaintenanceRecordRequest request) throws BadRequestException;

}
