package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liam on 09/09/2017.
 * {"error":[],
 * "result":{
 *      "XETHZUSD":{
 *          "ask":["308.75000","1","1.000"],
 *          "bid":["307.30000","20","20.000"],
 *          "close":["308.12000","0.88080000"],
 *          "volume":["14954.22445640","97271.42597282"],
 *          "volumeAveragePrice":["306.15693","310.71689"],
 *          "trades":[4555,19473],
 *          "low":["300.10000","295.22000"],
 *          "high":["312.00000","336.68000"],
 *          "openingPrice":"310.49000"
 *      }
 * }
 * }
 */

public class TickerDetail {
    // Ask array(<price>, <whole lot volume>, <lot volume>)
    @JsonProperty("a")
    private List<Double> Ask;

    //Bid array(<price>, <whole lot volume>, <lot volume>)
    @JsonProperty("b")
    private List<Double> Bid;

    //Last trade closed array(<price>, <lot volume>)
    @JsonProperty("c")
    private List<Double> Close;

    //Volume array(<today>, <last 24 hours>)
    @JsonProperty("v")
    private List<Double> Volume;

    //Volume weighted average price array(<today>, <last 24 hours>)
    @JsonProperty("p")
    private List<Double> VolumeAveragePrice;

    //Number of trades array(<today>, <last 24 hours>)
    @JsonProperty("t")
    private List<Double> Trades;

    //Low array(<today>, <last 24 hours>)
    @JsonProperty("l")
    private List<Double> Low;

    //High array(<today>, <last 24 hours>)
    @JsonProperty("h")
    private List<Double> High;

    //Today's opening price
    @JsonProperty("o")
    private Double OpeningPrice;

    public List<Double> getAsk() {
        return Ask;
    }

    public void setAsk(List<Double> ask) {
        Ask = ask;
    }

    public List<Double> getBid() {
        return Bid;
    }

    public void setBid(List<Double> bid) {
        Bid = bid;
    }

    public List<Double> getClose() {
        return Close;
    }

    public void setClose(List<Double> close) {
        Close = close;
    }

    public List<Double> getVolume() {
        return Volume;
    }

    public void setVolume(List<Double> volume) {
        Volume = volume;
    }

    public List<Double> getVolumeAveragePrice() {
        return VolumeAveragePrice;
    }

    public void setVolumeAveragePrice(List<Double> volumeAveragePrice) {
        VolumeAveragePrice = volumeAveragePrice;
    }

    public List<Double> getTrades() {
        return Trades;
    }

    public void setTrades(List<Double> trades) {
        Trades = trades;
    }

    public List<Double> getLow() {
        return Low;
    }

    public void setLow(List<Double> low) {
        Low = low;
    }

    public List<Double> getHigh() {
        return High;
    }

    public void setHigh(List<Double> high) {
        High = high;
    }

    public  Double getOpeningPrice() {
        return OpeningPrice;
    }

    public void setOpeningPrice(Double openingPrice) {
        OpeningPrice = openingPrice;
    }
}
