package com.rusher.okcoin.dto;

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
public class Currencies {
    private double BTC;
    private double BCC;
    private double ETC;
    private double ETH;
    private double LTC;
    private double CNY;

    public double getBTC() {
        return BTC;
    }

    public void setBTC(double BTC) {
        this.BTC = BTC;
    }

    public double getBCC() {
        return BCC;
    }

    public void setBCC(double BCC) {
        this.BCC = BCC;
    }

    public double getETC() {
        return ETC;
    }

    public void setETC(double ETC) {
        this.ETC = ETC;
    }

    public double getETH() {
        return ETH;
    }

    public void setETH(double ETH) {
        this.ETH = ETH;
    }

    public double getLTC() {
        return LTC;
    }

    public void setLTC(double LTC) {
        this.LTC = LTC;
    }

    public double getCNY() {
        return CNY;
    }

    public void setCNY(double CNY) {
        this.CNY = CNY;
    }
}
