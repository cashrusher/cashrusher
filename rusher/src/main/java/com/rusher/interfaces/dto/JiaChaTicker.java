package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Currency;
import com.rusher.Platform;

/**
 * Created by liam on 28/08/2017.
 */
public class JiaChaTicker {
    @JsonProperty("platform")
    private Platform platform;

    @JsonProperty("currency")
    private Currency currency;

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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
