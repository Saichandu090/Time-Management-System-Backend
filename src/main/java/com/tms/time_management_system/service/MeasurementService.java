package com.tms.time_management_system.service;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.model.Measurement;
import org.springframework.http.ResponseEntity;

public interface MeasurementService
{
    Measurement userLoggedIn(String email);

    ResponseEntity<?> userLoggedOut(int loginId);
}
