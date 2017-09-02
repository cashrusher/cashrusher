package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Platform;

/**
 * Created by liam on 02/09/2017.
 */
public class TestConnectionRequest {

    @JsonProperty("platform")
    private Platform platform;

    @JsonProperty("accesskey")
    private String accessKey;

    @JsonProperty("secretkey")
    private String secretKey;


    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

}