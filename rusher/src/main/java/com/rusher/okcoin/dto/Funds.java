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
public class Funds {
    @JsonProperty("borrow")
    private Currencies borrow;
    @JsonProperty("asset")
    private Asset asset;
    @JsonProperty("free")
    private Currencies free;
    @JsonProperty("freezed")
    private Currencies freezed;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset anAsset) {
        this.asset = anAsset;
    }

    public Currencies getBorrow() {
        return borrow;
    }

    public void setBorrow(Currencies borrow) {
        this.borrow = borrow;
    }

    public Currencies getFree() {
        return free;
    }

    public void setFree(Currencies free) {
        this.free = free;
    }

    public Currencies getFreezed() {
        return freezed;
    }

    public void setFreezed(Currencies freezed) {
        this.freezed = freezed;
    }
}
