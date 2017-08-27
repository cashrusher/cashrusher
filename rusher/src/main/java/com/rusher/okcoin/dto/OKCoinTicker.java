package com.rusher.okcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by liam on 27/08/2017.
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
public class OKCoinTicker {
    @JsonProperty("date")
    private Date date;

    @JsonProperty("ticker")
    private Ticker ticker;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
