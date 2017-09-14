package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Currency;
import com.rusher.Platform;

import java.util.Date;
import java.util.List;

/**
 * Created by liam on 27/08/2017.
 * <p>
 * {
 * "date": "1503817197",
 * "ticker": {
 * "buy": "2237.11",
 * "high": "2251.88",
 * "last": "2238.0",
 * "low": "2181.0",
 * "sell": "2238.0",
 * "vol": "151256.798"
 * }
 * }
 */
public class Ticker {
  @JsonProperty("error")
  private Error error;

  @JsonProperty("platform")
  private Platform platform;

  @JsonProperty("date")
  private Date date;

  @JsonProperty("buysellcurrency")
  private List<BuySellCurrency> buySellCurrencies;

  public Platform getPlatform() {
    return platform;
  }

  public void setPlatform(Platform platform) {
    this.platform = platform;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public List<BuySellCurrency> getBuySellCurrencies() {
    return buySellCurrencies;
  }

  public void setBuySellCurrencies(List<BuySellCurrency> buySellCurrencies) {
    this.buySellCurrencies = buySellCurrencies;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }
}
