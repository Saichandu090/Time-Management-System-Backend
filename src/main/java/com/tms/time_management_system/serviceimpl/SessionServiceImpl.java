package com.tms.time_management_system.serviceimpl;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.SessionResponseDTO;
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

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<?> userLoggedOut(Integer loginId)
    {
        Session session= sessionRepository.findById(loginId).orElseThrow(()->new RuntimeException("Session not Found"));
        session.setLogoutTime(LocalTime.now());
        Duration getSessionTime=Duration.between(session.getLoginTime(),LocalTime.now());
        session.setSessionTime(getSessionTime);
        sessionRepository.save(session);
        return new ResponseEntity<>(new JsonResponse(true,"Logout success",null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> pauseSession(Integer loginId)
    {
        Session session= sessionRepository.findById(loginId).orElseThrow(()->new RuntimeException("Session not Found"));
        session.setNoOfBreaks(session.getNoOfBreaks()+1);
        session.setPauseTime(LocalTime.now());
        Session rs=sessionRepository.save(session);
        return new ResponseEntity<>(new JsonResponse(true,"Breaktime started", null),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> playSession(Integer loginId)
    {
        Session session= sessionRepository.findById(loginId).orElseThrow(()->new RuntimeException("Session not Found"));
        Duration getBreakTime=Duration.between(session.getPauseTime(),LocalTime.now());
        if(session.getBreakTime()!=null){
            session.setBreakTime(session.getBreakTime().plus(getBreakTime));
        }else{
            session.setBreakTime(getBreakTime);
        }
        Session rs=sessionRepository.save(session);
        return new ResponseEntity<>(new JsonResponse(true,"Breaktime ended",null),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllSessions()
    {
        List<Session> allSessions=sessionRepository.findAll();
        List<SessionResponseDTO> responseDTOS=allSessions.stream().map(s->new SessionResponseDTO(s.getId(),s.getLoginTime(),s.getLogoutTime(),s.getNoOfBreaks(),s.getSessionTime(),s.getBreakTime())).toList();
        return new ResponseEntity<>(new JsonResponse(true,"All sessions Retrieved",responseDTOS),HttpStatus.OK);
    }
}
