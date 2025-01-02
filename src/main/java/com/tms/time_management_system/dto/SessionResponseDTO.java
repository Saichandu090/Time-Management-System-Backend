package com.tms.time_management_system.dto;

import java.time.Duration;
import java.time.LocalTime;

public class SessionResponseDTO
{
    private int id;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private int noOfBreaks;
    private Duration sessionTime;
    private Duration breakTime;
    private String email;

    public SessionResponseDTO()
    {

    }

    public SessionResponseDTO(int id, LocalTime loginTime, LocalTime logoutTime, int noOfBreaks, Duration sessionTime, Duration breakTime)
    {
        this.id = id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.noOfBreaks = noOfBreaks;
        this.sessionTime = sessionTime;
        this.breakTime = breakTime;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalTime getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime)
    {
        this.loginTime = loginTime;
    }

    public LocalTime getLogoutTime()
    {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime)
    {
        this.logoutTime = logoutTime;
    }

    public int getNoOfBreaks()
    {
        return noOfBreaks;
    }

    public void setNoOfBreaks(int noOfBreaks)
    {
        this.noOfBreaks = noOfBreaks;
    }

    public Duration getSessionTime()
    {
        return sessionTime;
    }

    public void setSessionTime(Duration sessionTime)
    {
        this.sessionTime = sessionTime;
    }

    public Duration getBreakTime()
    {
        return breakTime;
    }

    public void setBreakTime(Duration breakTime)
    {
        this.breakTime = breakTime;
    }
}
