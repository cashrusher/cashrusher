package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 03/09/2017.
 */
//
//{
//        "maxbuy_krakensell":1.80,
//        "maxsell_krakenbuy":1.73,
//        "maxbuy_bitfinexsell":2.12,
//        "maxsell_bitfinexbuy":2.13,
//        "maxbuy_minsell":1.98
//        }
public class ThresholdSettingRequest {
    @JsonProperty("maxbuy_krakensell")
    private double maxBuyKrakenSell;

    @JsonProperty("maxsell_krakenbuy")
    private double maxSellKrakenBuy;

    @JsonProperty("maxbuy_bitfinexsell")
    private double maxBuyBitfinexSell;

    @JsonProperty("maxsell_bitfinexbuy")
    private double maxSellBitfinexBuy;

    @JsonProperty("maxbuy_minsell")
    private double maxBuyMinSell;

    public double getMaxBuyKrakenSell() {
        return maxBuyKrakenSell;
    }

    public void setMaxBuyKrakenSell(double maxBuyKrakenSell) {
        this.maxBuyKrakenSell = maxBuyKrakenSell;
    }

    public double getMaxSellKrakenBuy() {
        return maxSellKrakenBuy;
    }

    public void setMaxSellKrakenBuy(double maxSellKrakenBuy) {
        this.maxSellKrakenBuy = maxSellKrakenBuy;
    }

    public double getMaxBuyBitfinexSell() {
        return maxBuyBitfinexSell;
    }

    public void setMaxBuyBitfinexSell(double maxBuyBitfinexSell) {
        this.maxBuyBitfinexSell = maxBuyBitfinexSell;
    }

    public double getMaxSellBitfinexBuy() {
        return maxSellBitfinexBuy;
    }

    public void setMaxSellBitfinexBuy(double maxSellBitfinexBuy) {
        this.maxSellBitfinexBuy = maxSellBitfinexBuy;
    }

    public double getMaxBuyMinSell() {
        return maxBuyMinSell;
    }

    public void setMaxBuyMinSell(double maxBuyMinSell) {
        this.maxBuyMinSell = maxBuyMinSell;
    }
}
