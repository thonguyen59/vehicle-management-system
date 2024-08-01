package com.assignment.vehiclemanagementsystem.entity;

import com.assignment.vehiclemanagementsystem.constant.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}
