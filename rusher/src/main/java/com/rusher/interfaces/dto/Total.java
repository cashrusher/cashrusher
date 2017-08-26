package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 26/08/2017.
 */
public class Total {
    @JsonProperty("ratio")
    private String ratio;
    @JsonProperty("cny")
    private double cny;
    @JsonProperty("usd")
    private double usd;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public double getCny() {
        return cny;
    }

    public void setCny(double cny) {
        this.cny = cny;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }
}
