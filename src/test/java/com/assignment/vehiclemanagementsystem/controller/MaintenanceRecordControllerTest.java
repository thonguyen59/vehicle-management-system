package com.assignment.vehiclemanagementsystem.controller;

import com.assignment.vehiclemanagementsystem.entity.MaintenanceRecord;
import com.assignment.vehiclemanagementsystem.payload.request.MaintenanceRecordRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.ResponseData;
import com.assignment.vehiclemanagementsystem.service.MaintenanceRecordService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MaintenanceRecordControllerTest {

    @InjectMocks
    private MaintenanceRecordController maintenanceRecordController;

    @Mock
    private MaintenanceRecordService maintenanceRecordService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllByVehicleId() {
        Long vehicleId = 1L;
        List<MaintenanceRecord> records = new ArrayList<>();
        when(maintenanceRecordService.getAllByVehicleId(vehicleId)).thenReturn(records);

        ResponseData<List<MaintenanceRecord>> result = maintenanceRecordController.getAllByVehicleId(vehicleId);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(records, result.getData());
    }

    @Test
    public void testCreate() throws BadRequestException {
        MaintenanceRecordRequest request = new MaintenanceRecordRequest();
        MaintenanceRecord record = new MaintenanceRecord();
        when(maintenanceRecordService.create(request)).thenReturn(record);

        ResponseData<MaintenanceRecord> result = maintenanceRecordController.create(request);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(record, result.getData());
    }

    @Test
    public void testUpdate() throws BadRequestException {
        Long id = 1L;
        MaintenanceRecordRequest request = new MaintenanceRecordRequest();
        MaintenanceRecord record = new MaintenanceRecord();
        when(maintenanceRecordService.update(id, request)).thenReturn(record);

        ResponseData<MaintenanceRecord> result = maintenanceRecordController.update(id, request);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(record, result.getData());
    }
}
