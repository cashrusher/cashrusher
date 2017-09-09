package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 * {"error":[],
 * "result":{
 * "descr":{
 * "order":"sell 0.02000000 ETHUSD @ market"
 * },
 * "txid":["OT5DME-3D5QU-JT7X3V"]
 * }
 * }
 */
public class TradeResult {

    @JsonProperty("descr")
    private Map<String, String> descr;

    @JsonProperty("txid")
    private List<String> exid;

    public Map<String, String> getDescr() {
        return descr;
    }

    public void setDescr(Map<String, String> descr) {
        this.descr = descr;
    }

    public List<String> getExid() {
        return exid;
    }

    public void setExid(List<String> exid) {
        this.exid = exid;
    }
}
