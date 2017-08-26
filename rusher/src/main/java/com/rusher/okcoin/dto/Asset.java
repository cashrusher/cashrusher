package com.rusher.okcoin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 26/08/2017.
 * {
 * "result": true,
 * "info": {
 * "funds": {
 * "borrow": {
 * "btc": "0",
 * "bcc": "0",
 * "etc": "0",
 * "eth": "0",
 * "ltc": "0",
 * "cny": "0"
 * },
 * "asset": {
 * "total": "876.904",
 * "net": "876.904"
 * },
 * "free": {
 * "btc": "0",
 * "bcc": "0",
 * "etc": "0",
 * "eth": "0.4",
 * "ltc": "0",
 * "cny": "0"
 * },
 * "freezed": {
 * "btc": "0",
 * "bcc": "0",
 * "etc": "0",
 * "eth": "0",
 * "ltc": "0",
 * "cny": "0"
 * }}}}
 */
public class Asset {
    @JsonProperty("total")
    private double total;
    @JsonProperty("net")
    private double net;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getNet() {
        return net;
    }

    public void setNet(double net) {
        this.net = net;
    }
}
