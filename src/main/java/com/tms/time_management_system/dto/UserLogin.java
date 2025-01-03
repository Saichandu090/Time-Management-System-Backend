package com.tms.time_management_system.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin
{
    private String email;
    private String password;
}
