package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liam on 28/08/2017.
 */
public class Abroad {
    @JsonProperty("tickers")
    private List<JiaChaTicker> tickers;

    @JsonProperty("calculate")
    private AbroadCalculate calculate;

    public List<JiaChaTicker> getTickers() {
        return tickers;
    }

    public void setTickers(List<JiaChaTicker> tickers) {
        this.tickers = tickers;
    }

    public AbroadCalculate getCalculate() {
        return calculate;
    }

    public void setCalculate(AbroadCalculate calculate) {
        this.calculate = calculate;
    }
}
