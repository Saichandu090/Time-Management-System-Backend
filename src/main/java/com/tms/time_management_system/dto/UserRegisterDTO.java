package com.tms.time_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO
{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Past
    private LocalDate dob;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String role;
}
