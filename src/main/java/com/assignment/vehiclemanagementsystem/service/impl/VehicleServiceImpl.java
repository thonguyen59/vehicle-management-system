package com.assignment.vehiclemanagementsystem.service.impl;

import com.assignment.vehiclemanagementsystem.constant.Role;
import com.assignment.vehiclemanagementsystem.entity.User;
import com.assignment.vehiclemanagementsystem.entity.Vehicle;
import com.assignment.vehiclemanagementsystem.payload.UserPrincipal;
import com.assignment.vehiclemanagementsystem.payload.request.VehicleRequest;
import com.assignment.vehiclemanagementsystem.repository.UserRepository;
import com.assignment.vehiclemanagementsystem.repository.VehicleRepository;
import com.assignment.vehiclemanagementsystem.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    @Override
    public List<Vehicle> getAllVehicles(UserPrincipal user) {
        List<Vehicle> result;

        if (Role.Admin.name().equals(user.getRole())) {
            result = vehicleRepository.findAll();
        } else {
            result = vehicleRepository.findAllByOwnerId(user.getId());
        }

        return result;
    }

    @Override
    public Vehicle create(VehicleRequest request) throws BadRequestException {
        User user = null;
        if (request.getOwnerUsername() != null) {
            Optional<User> userOptional = userRepository.findByUsername(request.getOwnerUsername());
            if (userOptional.isEmpty()) {
                throw new BadRequestException("Owner username is not exits!");
            }
            user = userOptional.get();
        }

        Vehicle vehicle = Vehicle.builder().color(request.getColor())
                .fuelType(request.getFuelType())
                .type(request.getType())
                .identificationNumber(request.getIdentificationNumber())
                .licensePlate(request.getLicensePlate())
                .make(request.getMake())
                .model(request.getModel())
                .owner(user)
                .year(request.getYear())
                .status(request.getStatus())
                .enabled(true).build();

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(Long id, VehicleRequest request) throws BadRequestException {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        if (vehicleOptional.isEmpty()) {
            throw new BadRequestException("Vehicle does not exist!");
        }

        User user = null;
        if (request.getOwnerUsername() != null) {
            Optional<User> userOptional = userRepository.findByUsername(request.getOwnerUsername());
            if (userOptional.isEmpty()) {
                throw new BadRequestException("Owner username does not exist!");
            }
            user = userOptional.get();
        }

        Vehicle vehicle = vehicleOptional.get();

        vehicle.setColor(request.getColor());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setType(request.getType());
        vehicle.setIdentificationNumber(request.getIdentificationNumber());
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setMake(request.getMake());
        vehicle.setModel(request.getModel());
        vehicle.setOwner(user);
        vehicle.setYear(request.getYear());
        vehicle.setStatus(request.getStatus());
        vehicle.setEnabled(request.getEnabled());

        return vehicleRepository.save(vehicle);
    }
}
