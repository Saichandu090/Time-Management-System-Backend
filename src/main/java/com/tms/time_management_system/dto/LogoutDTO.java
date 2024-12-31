package com.tms.time_management_system.dto;

import java.time.LocalTime;

public class LogoutDTO
{
    private int loginId;
    private int noOfBreaks;
    private String breakTime;

    public LogoutDTO(int loginId, int noOfBreaks, String breakTime) {
        this.loginId = loginId;
        this.noOfBreaks = noOfBreaks;
        this.breakTime = breakTime;
    }

    public LogoutDTO() {
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
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
