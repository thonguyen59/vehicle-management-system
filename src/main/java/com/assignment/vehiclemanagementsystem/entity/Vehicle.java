package com.assignment.vehiclemanagementsystem.entity;

import com.assignment.vehiclemanagementsystem.constant.FuelType;
import com.assignment.vehiclemanagementsystem.constant.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "VEHICLE")
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    //Vehicle Identification Number
    @Column(name = "identification_number", unique = true)
    private String identificationNumber;

    @Column(name = "color")
    private String color;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "status")
    private String status;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MaintenanceRecord> maintenanceRecords = new ArrayList<>();
}
