package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 02/09/2017.
 */
public class SettingResponse  {
    @JsonProperty("status")
    private Status status;

    public SettingResponse(Status status) {
        this.status = status;
    }

    public SettingResponse() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
