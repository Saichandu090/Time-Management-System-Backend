package com.tms.time_management_system.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO
{
    private String email;
    private String role;
    private int loginId;
}
