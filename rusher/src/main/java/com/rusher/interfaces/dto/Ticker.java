package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Currency;
import com.rusher.Platform;

import java.util.Date;

/**
 * Created by liam on 27/08/2017.
 * <p>
 * {
 * "date": "1503817197",
 * "ticker": {
 * "buy": "2237.11",
 * "high": "2251.88",
 * "last": "2238.0",
 * "low": "2181.0",
 * "sell": "2238.0",
 * "vol": "151256.798"
 * }
 * }
 */
public class Ticker {
    @JsonProperty("platform")
    private Platform platform;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("buy")
    private double buy;

    @JsonProperty("sell")
    private double sell;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
