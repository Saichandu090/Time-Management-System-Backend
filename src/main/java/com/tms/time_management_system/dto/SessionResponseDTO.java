package com.tms.time_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionResponseDTO
{
    private int id;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private int noOfBreaks;
    private Duration sessionTime;
    private Duration breakTime;
    private String email;
}
