package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 03/09/2017.
 */
public enum  ExchangeKey {
    @JsonProperty("cnyusd")
    CNYUSD("cnyusd"),
    @JsonProperty("cnyjyp")
    CNYJYP("cnyjyp");
    private String exchangeKey;

    ExchangeKey(String exchangeKey) {
        this.exchangeKey = exchangeKey;
    }

    public String getExchangeKey() {
        return exchangeKey;
    }

    public static ExchangeKey getExchangeKey(String exchangeKey) {
        for (ExchangeKey c : values()) {
            if (c.getExchangeKey().equalsIgnoreCase(exchangeKey)) {
                return c;
            }
        }
        throw new IllegalArgumentException(exchangeKey + " is not a valid exchangeKey");
    }
}
