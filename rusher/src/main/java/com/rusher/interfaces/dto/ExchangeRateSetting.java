package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 03/09/2017.
 */
//
//{
//        "exchangerate":[
//        {"cnyusd":0.1526},
//        {"cnyjyp":16.8100}
//        ]
//        }
public class ExchangeRateSetting {

    @JsonProperty("exchangerate")
    private List<Map<ExchangeKey, Double>> exchangeRate;

    public List<Map<ExchangeKey, Double>> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(List<Map<ExchangeKey, Double>> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
