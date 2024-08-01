package com.assignment.vehiclemanagementsystem.controller;

import com.assignment.vehiclemanagementsystem.config.JwtService;
import com.assignment.vehiclemanagementsystem.entity.Vehicle;
import com.assignment.vehiclemanagementsystem.payload.UserPrincipal;
import com.assignment.vehiclemanagementsystem.payload.request.VehicleRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.ResponseData;
import com.assignment.vehiclemanagementsystem.service.VehicleService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @Mock
    private JwtService jwtService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllVehicles() {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername("testUser");
        userPrincipal.setId(1L);
        userPrincipal.setRole("Admin");

        List<Vehicle> vehicles = new ArrayList<>();
        when(vehicleService.getAllVehicles(userPrincipal)).thenReturn(vehicles);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer dummyJwtToken");

        when(jwtService.extractUsername("dummyJwtToken")).thenReturn("testUser");
        when(jwtService.extractUserPrincipal("dummyJwtToken")).thenReturn(userPrincipal);

        ResponseData<List<Vehicle>> result = vehicleController.getVehicles(request);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(vehicles, result.getData());
    }

    @Test
    public void testCreate() throws BadRequestException {
        VehicleRequest request = new VehicleRequest();
        Vehicle vehicle = new Vehicle();
        when(vehicleService.create(request)).thenReturn(vehicle);

        ResponseData<Vehicle> result = vehicleController.createVehicle(request);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(vehicle, result.getData());
    }

    @Test
    public void testUpdate() throws BadRequestException {
        Long id = 1L;
        VehicleRequest request = new VehicleRequest();
        Vehicle vehicle = new Vehicle();
        when(vehicleService.update(id, request)).thenReturn(vehicle);

        ResponseData<Vehicle> result = vehicleController.updateVehicle(id, request);
        assertEquals(HttpStatus.OK.value(), result.getStatus());
        assertEquals(vehicle, result.getData());
    }
}
