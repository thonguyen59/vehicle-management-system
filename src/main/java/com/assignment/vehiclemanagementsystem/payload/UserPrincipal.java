package com.assignment.vehiclemanagementsystem.payload;

import com.assignment.vehiclemanagementsystem.constant.Role;
import lombok.Data;

@Data
public class UserPrincipal {
    private String username;
    private Long id;
    private String role;
}
