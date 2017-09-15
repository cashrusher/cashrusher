package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 * {"error":[],
 * "result":{
 * "XETHZUSD":{
 * "a":["308.75000","1","1.000"],
 * "b":["307.30000","20","20.000"],
 * "c":["308.12000","0.88080000"],
 * "v":["14954.22445640","97271.42597282"],
 * "p":["306.15693","310.71689"],
 * "t":[4555,19473],
 * "l":["300.10000","295.22000"],
 * "h":["312.00000","336.68000"],
 * "o":"310.49000"
 * }
 * }
 * }
 */

public class KrakenTicker {

    @JsonProperty("error")
    private List<Map<String, String>> error;

    @JsonProperty("result")
    private Map<String, TickerDetail> result;


    public List<Map<String, String>> getError() {
        return error;
    }

    public void setError(List<Map<String, String>> error) {
        this.error = error;
    }

    public Map<String, TickerDetail> getResult() {
        return result;
    }

    public void setResult(Map<String, TickerDetail> result) {
        this.result = result;
    }
}
