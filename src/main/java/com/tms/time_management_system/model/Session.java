package com.tms.time_management_system.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime loginTime;
    private LocalTime logoutTime;
    private int noOfBreaks;
    private Duration sessionTime;
    private LocalTime pauseTime;
    private LocalTime playTime;
    private Duration breakTime;

    @ManyToMany
    private List<User> users;

    public Session() {
    }

    public Session(Integer id, LocalTime loginTime, LocalTime logoutTime, int noOfBreaks, Duration sessionTime, LocalTime pauseTime, LocalTime playTime, Duration breakTime, List<User> users)
    {
        this.id = id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.noOfBreaks = noOfBreaks;
        this.sessionTime = sessionTime;
        this.pauseTime = pauseTime;
        this.playTime = playTime;
        this.breakTime = breakTime;
        this.users = users;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
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

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
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

    public LocalTime getPauseTime()
    {
        return pauseTime;
    }

    public void setPauseTime(LocalTime pauseTime)
    {
        this.pauseTime = pauseTime;
    }

    public LocalTime getPlayTime()
    {
        return playTime;
    }

    public void setPlayTime(LocalTime playTime)
    {
        this.playTime = playTime;
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
