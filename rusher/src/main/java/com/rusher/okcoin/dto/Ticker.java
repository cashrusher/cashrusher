package com.rusher.okcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 27/08/2017.
 * {"date":"1503817881",
 * "ticker":
 * {"buy":"2234.02","high":"2251.88","last":"2236.0","low":"2181.0","sell":"2236.0","vol":"148985.002"}}
 */
public class Ticker {
    @JsonProperty("buy")
    private double buy;

    @JsonProperty("sell")
    private double sell;

    @JsonProperty("last")
    private double last;

    @JsonProperty("high")
    private double high;

    @JsonProperty("low")
    private double low;

    @JsonProperty("vol")
    private double vol;


    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
