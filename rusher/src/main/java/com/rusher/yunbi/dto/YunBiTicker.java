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
public class YunBiTicker {
    @JsonProperty("at")
    private float timeStamp;

    @JsonProperty("ticker")
    private Ticker ticker;

    public float getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(float timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
