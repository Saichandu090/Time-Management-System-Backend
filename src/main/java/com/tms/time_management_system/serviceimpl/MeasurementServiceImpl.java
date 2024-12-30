package com.tms.time_management_system.serviceimpl;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.exception.UserNotFoundException;
import com.tms.time_management_system.model.Measurement;
import com.tms.time_management_system.model.User;
import com.tms.time_management_system.repository.MeasurementRepository;
import com.tms.time_management_system.repository.UserRepository;
import com.tms.time_management_system.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class MeasurementServiceImpl implements MeasurementService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Override
    public Measurement userLoggedIn(String email)
    {
        User user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found"));
        Measurement measurement=new Measurement();
        if(measurement.getUsers()==null)
            measurement.setUsers(new ArrayList<>());
        measurement.getUsers().add(user);
        measurement.setLoginTime(LocalTime.now());
        measurement.setLogoutTime(null);
        return measurementRepository.save(measurement);
    }

    @Override
    public ResponseEntity<?> userLoggedOut(int loginId)
    {
        Measurement session=measurementRepository.findById(loginId).orElseThrow(()->new RuntimeException("Session not Found"));
        session.setLogoutTime(LocalTime.now());
        measurementRepository.save(session);
        return new ResponseEntity<>(new JsonResponse(true,"Logout success",null), HttpStatus.OK);
    }
}
