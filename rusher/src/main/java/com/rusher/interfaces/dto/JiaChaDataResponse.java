package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 28/08/2017.
 */
public class JiaChaDataResponse {
    @JsonProperty("mainland")
    private Mainland mainland;

    @JsonProperty("abroad")
    private Abroad abroad;

    @JsonProperty("cnyusdratio")
    private double cnyUSDRatio;

    public Mainland getMainland() {
        return mainland;
    }

    public void setMainland(Mainland mainland) {
        this.mainland = mainland;
    }

    public Abroad getAbroad() {
        return abroad;
    }

    public void setAbroad(Abroad abroad) {
        this.abroad = abroad;
    }

    public double getCnyUSDRatio() {
        return cnyUSDRatio;
    }

    public void setCnyUSDRatio(double cnyUSDRatio) {
        this.cnyUSDRatio = cnyUSDRatio;
    }
}
