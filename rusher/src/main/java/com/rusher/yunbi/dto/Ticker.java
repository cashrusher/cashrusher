package com.rusher.yunbi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 27/08/2017.
 * {
 * "at":1503818851,
 * "ticker":{
 * "buy":"28950.6",
 * "sell":"29000.0",
 * "low":"28480.0",
 * "high":"29149.99",
 * "last":"28950.3",
 * "vol":"771.9786"
 * }}
 */
public class Ticker {
    @JsonProperty("buy")
    private double buy;

    @JsonProperty("sell")
    private double sell;

    @JsonProperty("low")
    private double low;

    @JsonProperty("high")
    private double high;

    @JsonProperty("last")
    private double last;

    @JsonProperty("vol")
    private double vol;

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

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }
}
