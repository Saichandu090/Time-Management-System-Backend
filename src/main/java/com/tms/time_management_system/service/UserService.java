package com.tms.time_management_system.service;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserLogin;
import com.tms.time_management_system.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface UserService
{
    ResponseEntity<JsonResponse> registerUser(UserRegisterDTO registerDTO);

    ResponseEntity<JsonResponse> loginUser(UserLogin userLogin);
}
