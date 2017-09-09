package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 * <p>
 * {"error":[],
 * "result":{
 * "descr":{
 * "order":"sell 0.02000000 ETHUSD @ market"
 * },
 * "txid":["OT5DME-3D5QU-JT7X3V"]
 * }
 * }
 */
public class KrakenTradeResponse {
    @JsonProperty("error")
    private List<Map<String,String>> error;

    @JsonProperty("result")
    private TradeResult result;

    public List<Map<String, String>> getError() {
        return error;
    }

    public void setError(List<Map<String, String>> error) {
        this.error = error;
    }

    public TradeResult getResult() {
        return result;
    }

    public void setResult(TradeResult result) {
        this.result = result;
    }
}
