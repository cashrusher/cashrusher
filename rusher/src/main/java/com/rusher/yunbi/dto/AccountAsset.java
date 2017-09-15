package com.rusher.yunbi.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by liam on 13/08/2017.
 */
public class AccountAsset {

    @JSONField(name = "currency")
    private String currency;

    @JSONField(name = "balance")
    private Double availBalance;

    @JSONField(name = "locked")
    private Double lockedBalance;

    public Double getLockedBalance() {
        return lockedBalance;
    }

    public void setLockedBalance(Double lockedBalance) {
        this.lockedBalance = lockedBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(Double availBalance) {
        this.availBalance = availBalance;
    }
}
