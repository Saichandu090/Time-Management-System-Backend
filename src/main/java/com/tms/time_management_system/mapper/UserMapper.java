package com.tms.time_management_system.mapper;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.model.User;
import com.tms.time_management_system.serviceimpl.JWTService;
import com.tms.time_management_system.serviceimpl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{
    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    public UserDetails validateUser(String authHeader)
    {
        String token=authHeader.substring(7);
        String email=jwtService.extractEmail(token);

        if(email!=null)
        {
            return context.getBean(MyUserDetailsService.class).loadUserByUsername(email);
        }
        return null;
    }

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
