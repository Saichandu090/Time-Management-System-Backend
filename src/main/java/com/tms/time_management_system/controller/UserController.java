package com.tms.time_management_system.controller;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserLogin;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<JsonResponse> registerUser(@Valid @RequestBody UserRegisterDTO registerDTO)
    {
        return userService.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> loginUser(@Valid @RequestBody UserLogin userLogin)
    {
        return userService.loginUser(userLogin);
    }
}
