package com.tms.time_management_system.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse
{
    private boolean result;
    private String message;
    private List<?> data;
}
