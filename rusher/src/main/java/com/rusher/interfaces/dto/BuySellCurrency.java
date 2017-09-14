package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rusher.Currency;

/**
 * Author: Liam
 * Date: 2017/9/14
 */
public class BuySellCurrency {

  @JsonProperty("currency")
  private Currency currency;

  @JsonProperty("buy")
  private double buy;

  @JsonProperty("sell")
  private double sell;

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public double getBuy() {
    return buy;
  }

  public void setBuy(double buy) {
    this.buy = buy;
  }

  public double getSell() {
    return sell;
  }

  public void setSell(double sell) {
    this.sell = sell;
  }
}
