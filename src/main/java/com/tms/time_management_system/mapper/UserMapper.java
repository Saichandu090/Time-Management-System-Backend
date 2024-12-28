package com.tms.time_management_system.mapper;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserMapper
{
    public static ResponseEntity<JsonResponse> convertUser(User user)
    {
        JsonResponse response=new JsonResponse();
        response.setResult(true);
        response.setMessage("User Registered Successfully");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public User getUser(UserRegisterDTO registerDTO)
    {
        User user=new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setDob(registerDTO.getDob());
        user.setRole(registerDTO.getRole());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        return user;
    }
}
