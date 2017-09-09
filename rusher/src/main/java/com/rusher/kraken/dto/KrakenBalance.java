package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 26/08/2017.
 * {"error":[],"result":{"XETH":"150.5577376700"}}
 */

public class KrakenBalance {
    @JsonProperty("error")
    private List<Map<String, String>> error;

    @JsonProperty("result")
    private Map<String, Double> result;

    public List<Map<String, String>> getError() {
        return error;
    }

    public void setError(List<Map<String, String>> error) {
        this.error = error;
    }

    public Map<String, Double> getResult() {
        return result;
    }

    public void setResult(Map<String, Double> result) {
        this.result = result;
    }
}
