package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 02/09/2017.
 */
public class SettingRequest extends TestConnectionRequest {

    @JsonProperty("fetchrate")
    private int fetchRate;

    public int getFetchRate() {
        return fetchRate;
    }

    public void setFetchRate(int fetchRate) {
        this.fetchRate = fetchRate;
    }
}
