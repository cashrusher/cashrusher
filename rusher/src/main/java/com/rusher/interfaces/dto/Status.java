package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 02/09/2017.
 */
public enum Status {
    @JsonProperty("failed")
    Failed("failed"),
    @JsonProperty("success")
    Success("success");
    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status getStatus(String status) {
        for (Status c : values()) {
            if (c.getStatus().equalsIgnoreCase(status)) {
                return c;
            }
        }
        throw new IllegalArgumentException(status + " is not a valid Status");
    }
}
