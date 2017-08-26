package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 26/08/2017.
 */
public class PlatformAsset {
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("available")
    private double available;

    @JsonProperty("locked")
    private double locked;

    @JsonProperty("cny")
    private double cny;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public double getLocked() {
        return locked;
    }

    public void setLocked(double locked) {
        this.locked = locked;
    }

    public double getCny() {
        return cny;
    }

    public void setCny(double cny) {
        this.cny = cny;
    }
}
