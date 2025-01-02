package com.tms.time_management_system.controller;

import com.tms.time_management_system.dto.*;
import com.tms.time_management_system.mapper.UserMapper;
import com.tms.time_management_system.service.SessionService;
import com.tms.time_management_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private SessionService sessionService;

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

    @DeleteMapping("/logout/{loginId}")
    private ResponseEntity<?> onLogOut(@RequestHeader("Authorization")String authHeader, @PathVariable Integer loginId)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null)
        {
            return sessionService.userLoggedOut(loginId);
        }
        return new ResponseEntity<>(new JsonResponse(false,"Logout Failed",null), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/pause")
    public ResponseEntity<?> onPause(@RequestHeader("Authorization")String authHeader,@RequestBody LogoutDTO logoutDTO)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null)
        {
            return sessionService.pauseSession(logoutDTO.getLoginId());
        }
        return new ResponseEntity<>(new JsonResponse(false,"Request Failed",null), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/play")
    public ResponseEntity<?> onPlay(@RequestHeader("Authorization")String authHeader,@RequestBody LogoutDTO logoutDTO)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null)
        {
            return sessionService.playSession(logoutDTO.getLoginId());
        }
        return new ResponseEntity<>(new JsonResponse(false,"Request Failed",null), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getUserSessions")
    public ResponseEntity<?> getAllSessions(@RequestHeader("Authorization")String authHeader)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
        {
            return sessionService.getAllSessions();
        }
        return new ResponseEntity<>(new JsonResponse(false,"Request Failed",null), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteSession/{sessionId}")
    public ResponseEntity<?> deleteSession(@RequestHeader("Authorization")String authHeader,@PathVariable int sessionId)
    {
        UserDetails userDetails=userMapper.validateUser(authHeader);
        if(userDetails!=null && userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
        {
            return sessionService.deleteSession(sessionId);
        }
        return new ResponseEntity<>(new JsonResponse(false,"Request Failed",null), HttpStatus.BAD_REQUEST);
    }
}
