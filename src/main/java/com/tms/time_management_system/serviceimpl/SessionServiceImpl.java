package com.tms.time_management_system.serviceimpl;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.LogoutDTO;
import com.tms.time_management_system.exception.UserNotFoundException;
import com.tms.time_management_system.model.Session;
import com.tms.time_management_system.model.User;
import com.tms.time_management_system.repository.SessionRepository;
import com.tms.time_management_system.repository.UserRepository;
import com.tms.time_management_system.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class SessionServiceImpl implements SessionService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session userLoggedIn(String email)
    {
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
        Session session =new Session();
        if(session.getUsers()==null)
            session.setUsers(new ArrayList<>());
        session.getUsers().add(user);
        session.setLoginTime(LocalTime.now());
        session.setLogoutTime(null);
        return sessionRepository.save(session);
    }

    @Override
    public ResponseEntity<?> userLoggedOut(LogoutDTO logoutDTO)
    {
        Session session= sessionRepository.findById(logoutDTO.getLoginId()).orElseThrow(()->new RuntimeException("Session not Found"));
        session.setLogoutTime(LocalTime.now());
        session.setBreakTime(logoutDTO.getBreakTime());
        session.setNoOfBreaks(logoutDTO.getNoOfBreaks());
        sessionRepository.save(session);
        return new ResponseEntity<>(new JsonResponse(true,"Logout success",null), HttpStatus.OK);
    }
}
