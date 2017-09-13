package com.rusher.bitfinex.dto;

/**
 * Created by liam on 10/09/2017.
 * WALLET_TYPE	string	Wallet name (exchange, margin, funding)
 * CURRENCY	string	Currency (fUSD, etc)
 * BALANCE	float	Wallet balance
 * UNSETTLED_INTEREST	float	Unsettled interest
 * BALANCE_AVAILABLE	float / null	Amount not tied up in active orders, positions or funding (null if the value is not fresh enough).
 */
public class BitfinexBalance {

    //Wallet name (exchange, margin, funding)
    private String walletType;

    //Currency (fUSD, etc)
    private String currency;

    //Wallet balance
    private Double balance;

    //Unsettled interest
    private Double unsettledInterest;

    //Amount not tied up in active orders, positions or funding (null if the value is not fresh enough).
    private Double balanceAvailable;

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getUnsettledInterest() {
        return unsettledInterest;
    }

    public void setUnsettledInterest(Double unsettledInterest) {
        this.unsettledInterest = unsettledInterest;
    }

    public Double getBalanceAvailable() {
        return balanceAvailable;
    }

    public void setBalanceAvailable(Double balanceAvailable) {
        this.balanceAvailable = balanceAvailable;
    }
}
