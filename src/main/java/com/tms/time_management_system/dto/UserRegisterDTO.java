package com.tms.time_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO
{
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String password;
    private String role;
}
