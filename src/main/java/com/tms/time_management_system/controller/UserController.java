package com.tms.time_management_system.controller;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserDTO;
import com.tms.time_management_system.dto.UserLogin;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.mapper.UserMapper;
import com.tms.time_management_system.service.MeasurementService;
import com.tms.time_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MeasurementService measurementService;

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

    @PutMapping("/logout/{loginId}")
    private ResponseEntity<?> onLogOut(@RequestHeader("Authorization")String authHeader, @PathVariable int loginId, @RequestBody UserDTO userDTO)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null)
        {
            return measurementService.userLoggedOut(loginId);
        }
        return new ResponseEntity<>(new JsonResponse(false,"Logout Failed",null), HttpStatus.BAD_REQUEST);
    }
}
