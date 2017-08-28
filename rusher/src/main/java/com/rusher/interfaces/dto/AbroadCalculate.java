package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Platform;

/**
 * Created by liam on 28/08/2017.
 */
public class AbroadCalculate {
    @JsonProperty("platform")
    private Platform platform;
    @JsonProperty("sellratio")
    private double sellRatio;
    @JsonProperty("buyratio")
    private double buyRatio;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public double getSellRatio() {
        return sellRatio;
    }

    public void setSellRatio(double sellRatio) {
        this.sellRatio = sellRatio;
    }

    public double getBuyRatio() {
        return buyRatio;
    }

    public void setBuyRatio(double buyRatio) {
        this.buyRatio = buyRatio;
    }
}
