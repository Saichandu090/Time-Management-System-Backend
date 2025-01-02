package com.tms.time_management_system.dto;

import java.time.LocalTime;

public class LogoutDTO
{
    private int loginId;

    public LogoutDTO(int loginId)
    {
        this.loginId = loginId;
    }

    public LogoutDTO() {
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }
}
