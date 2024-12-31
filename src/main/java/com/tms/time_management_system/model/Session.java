package com.tms.time_management_system.model;

import jakarta.persistence.*;

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
    private String breakTime;

    @ManyToMany
    private List<User> users;

    public Session() {
    }

    public Session(Integer id, LocalTime loginTime, LocalTime logoutTime, int noOfBreaks, String breakTime, List<User> users) {
        this.id = id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.noOfBreaks = noOfBreaks;
        this.breakTime = breakTime;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getNoOfBreaks() {
        return noOfBreaks;
    }

    public void setNoOfBreaks(int noOfBreaks) {
        this.noOfBreaks = noOfBreaks;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }
}
