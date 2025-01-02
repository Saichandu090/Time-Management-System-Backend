package com.tms.time_management_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserLogin;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest
{
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    public void registerUserTest() throws Exception
    {
        UserRegisterDTO registerDTO=new UserRegisterDTO("Sai","Chandu", LocalDate.of(2002,8,24),"marrisaichandu143@gmail.com","saichandu090","ADMIN");
        String reqBody=objectMapper.writeValueAsString(registerDTO);

        JsonResponse jsonResponse=new JsonResponse(true,"User Registered Successfully",null);
        when(userService.registerUser(registerDTO))
                .thenReturn(new ResponseEntity<>(jsonResponse, HttpStatus.CREATED));

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqBody))
                .andExpect(status().isCreated());
    }

    @Test
    public void loginUserTest() throws Exception
    {
        UserLogin userLogin=new UserLogin("marrisaichandu143@gmail.com","saichandu090");
        String reqBody=objectMapper.writeValueAsString(userLogin);

        JsonResponse jsonResponse=new JsonResponse(true,"Login success",null);

        when(userService.loginUser(userLogin))
                .thenReturn(new ResponseEntity<>(jsonResponse,HttpStatus.OK));

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqBody))
                .andExpect(status().isOk());
    }
}