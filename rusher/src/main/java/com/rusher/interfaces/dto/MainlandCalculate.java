package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 28/08/2017.
 */
public class MainlandCalculate {
    @JsonProperty("maxsell")
    private double maxSell;
    @JsonProperty("minbuy")
    private double minBuy;
    @JsonProperty("maxsellminbuyratio")
    private double maxSellMinBuyRatio;


    public double getMaxSell() {
        return maxSell;
    }

    public void setMaxSell(double maxSell) {
        this.maxSell = maxSell;
    }

    public double getMinBuy() {
        return minBuy;
    }

    public void setMinBuy(double minBuy) {
        this.minBuy = minBuy;
    }

    public double getMaxSellMinBuyRatio() {
        return maxSellMinBuyRatio;
    }

    public void setMaxSellMinBuyRatio(double maxSellMinBuyRatio) {
        this.maxSellMinBuyRatio = maxSellMinBuyRatio;
    }
}
