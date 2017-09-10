package com.rusher.bitfinex.dto;

/**
 * Created by liam on 10/09/2017.
 * <p>
 * SYMBOL,
 * BID,
 * BID_SIZE,
 * ASK,
 * ASK_SIZE,
 * DAILY_CHANGE,
 * DAILY_CHANGE_PERC,
 * LAST_PRICE,
 * VOLUME,
 * HIGH,
 * LOW
 * <p>
 * Fields	Type	Description
 * FRR	float	Flash Return Rate - average of all fixed rate funding over the last hour
 * BID	float	Price of last highest bid
 * BID_PERIOD	int	Bid period covered in days
 * BID_SIZE	float	Size of the last highest bid
 * ASK	float	Price of last lowest ask
 * ASK_PERIOD	int	Ask period covered in days
 * ASK_SIZE	float	Size of the last lowest ask
 * DAILY_CHANGE	float	Amount that the last price has changed since yesterday
 * DAILY_CHANGE_PERC	float	Amount that the price has changed expressed in percentage terms
 * LAST_PRICE	float	Price of the last trade
 * VOLUME	float	Daily volume
 * HIGH	float	Daily high
 * LOW	float	Daily low
 */
public class BitfinexTicker {

    private String symbol;

    private Double flashReturnRate;

    private Double PriceOfLastHighestBid;

    private Integer bidPeriodCoveredInDays;

    private Double sizeOfLastHighestBid;

    private Double priceOfLastLowestAsk;

    private Integer askPeriodCoveredInDays;

    private Double sizeOfLastLowestAsk;

    private Double dailyChange;

    private Double dailyChangePercentage;

    private Double lastPrice;

    private Double dailyVolume;

    private Double dailyHigh;

    private Double dailyLow;

    public Double getFlashReturnRate() {
        return flashReturnRate;
    }

    public void setFlashReturnRate(Double flashReturnRate) {
        this.flashReturnRate = flashReturnRate;
    }

    public Double getPriceOfLastHighestBid() {
        return PriceOfLastHighestBid;
    }

    public void setPriceOfLastHighestBid(Double priceOfLastHighestBid) {
        PriceOfLastHighestBid = priceOfLastHighestBid;
    }

    public Integer getBidPeriodCoveredInDays() {
        return bidPeriodCoveredInDays;
    }

    public void setBidPeriodCoveredInDays(Integer bidPeriodCoveredInDays) {
        this.bidPeriodCoveredInDays = bidPeriodCoveredInDays;
    }

    public Double getSizeOfLastHighestBid() {
        return sizeOfLastHighestBid;
    }

    public void setSizeOfLastHighestBid(Double sizeOfLastHighestBid) {
        this.sizeOfLastHighestBid = sizeOfLastHighestBid;
    }

    public Double getPriceOfLastLowestAsk() {
        return priceOfLastLowestAsk;
    }

    public void setPriceOfLastLowestAsk(Double priceOfLastLowestAsk) {
        this.priceOfLastLowestAsk = priceOfLastLowestAsk;
    }

    public Integer getAskPeriodCoveredInDays() {
        return askPeriodCoveredInDays;
    }

    public void setAskPeriodCoveredInDays(Integer askPeriodCoveredInDays) {
        this.askPeriodCoveredInDays = askPeriodCoveredInDays;
    }

    public Double getSizeOfLastLowestAsk() {
        return sizeOfLastLowestAsk;
    }

    public void setSizeOfLastLowestAsk(Double sizeOfLastLowestAsk) {
        this.sizeOfLastLowestAsk = sizeOfLastLowestAsk;
    }

    public Double getDailyChange() {
        return dailyChange;
    }

    public void setDailyChange(Double dailyChange) {
        this.dailyChange = dailyChange;
    }

    public Double getDailyChangePercentage() {
        return dailyChangePercentage;
    }

    public void setDailyChangePercentage(Double dailyChangePercentage) {
        this.dailyChangePercentage = dailyChangePercentage;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getDailyVolume() {
        return dailyVolume;
    }

    public void setDailyVolume(Double dailyVolume) {
        this.dailyVolume = dailyVolume;
    }

    public Double getDailyHigh() {
        return dailyHigh;
    }

    public void setDailyHigh(Double dailyHigh) {
        this.dailyHigh = dailyHigh;
    }

    public Double getDailyLow() {
        return dailyLow;
    }

    public void setDailyLow(Double dailyLow) {
        this.dailyLow = dailyLow;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
