package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liam on 28/08/2017.
 */
public class Mainland {
    @JsonProperty("tickers")
    private List<JiaChaTicker> tickers;

    @JsonProperty("calculate")
    private MainlandCalculate calculate;

    public List<JiaChaTicker> getTickers() {
        return tickers;
    }

    public void setTickers(List<JiaChaTicker> tickers) {
        this.tickers = tickers;
    }

    public MainlandCalculate getCalculate() {
        return calculate;
    }

    public void setCalculate(MainlandCalculate calculate) {
        this.calculate = calculate;
    }
}
