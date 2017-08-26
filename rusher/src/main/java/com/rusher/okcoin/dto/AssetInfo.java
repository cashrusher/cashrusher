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
public class AssetInfo {
    @JsonProperty("funds")
    private Funds funds;

    public Funds getFunds() {
        return funds;
    }

    public void setFunds(Funds funds) {
        this.funds = funds;
    }
}
