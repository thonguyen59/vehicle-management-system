package com.assignment.vehiclemanagementsystem.controller;

import com.assignment.vehiclemanagementsystem.config.JwtService;
import com.assignment.vehiclemanagementsystem.entity.Vehicle;
import com.assignment.vehiclemanagementsystem.payload.UserPrincipal;
import com.assignment.vehiclemanagementsystem.payload.request.VehicleRequest;
import com.assignment.vehiclemanagementsystem.payload.respone.ResponseData;
import com.assignment.vehiclemanagementsystem.service.VehicleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final JwtService jwtService;
    @GetMapping("")
    public ResponseData<List<Vehicle>> getVehicles(HttpServletRequest request) {
        UserPrincipal user = jwtService.getUserFromToken(request.getHeader("Authorization"));

        return new ResponseData(HttpStatus.OK.value(), "Get vehicle list successful", vehicleService.getAllVehicles(user));
    }

    @PostMapping("")
    public ResponseData<Vehicle> createVehicle(@RequestBody VehicleRequest request) throws BadRequestException {
        return new ResponseData(HttpStatus.OK.value(), "Create vehicle successful", vehicleService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseData<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody VehicleRequest request) throws BadRequestException {
        return new ResponseData(HttpStatus.OK.value(), "Update vehicle successful", vehicleService.update(id, request));
    }


}
