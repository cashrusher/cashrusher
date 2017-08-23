package com.rusher.yunbi.dto;

/**
 * Created by liam on 13/08/2017.
 */
public class MarketAsset {
    private Long id;
    private Long accountId;
    private Market market;
    private Currency currency = Currency.USD;
    private Double availBalance = 0.0;
    private Double lockedBalance = 0.0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }


    public Double getAvailBalance() {
        return availBalance;
    }

    public void setAvailBalance(Double availBalance) {
        this.availBalance = availBalance;
    }

    public Double getLockedBalance() {
        return lockedBalance;
    }

    public void setLockedBalance(Double lockedBalance) {
        this.lockedBalance = lockedBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
