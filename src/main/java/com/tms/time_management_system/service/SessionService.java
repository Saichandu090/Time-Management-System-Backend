package com.tms.time_management_system.service;

import com.tms.time_management_system.dto.LogoutDTO;
import com.tms.time_management_system.model.Session;
import org.springframework.http.ResponseEntity;

public interface SessionService
{
    Session userLoggedIn(String email);

    ResponseEntity<?> userLoggedOut(Integer loginId);

    ResponseEntity<?> pauseSession(Integer loginId);

    ResponseEntity<?> playSession(Integer loginId);

    ResponseEntity<?> getAllSessions();
}
