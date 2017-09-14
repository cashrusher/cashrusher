package com.rusher.interfaces.ws.support;

import com.rusher.Currency;
import com.rusher.interfaces.dto.BuySellCurrency;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.db.SystemSetting;

/**
 * Created by liam on 14/09/2017.
 * <p>
 * The algorithm only compare price with fix currency, that is CNY
 */
public class AlgorithmSupportJiaCha {

  public static Ticker getHighestBuy(Ticker... tickers) {
    double highestBuy = 0d;
    Ticker highestBuyTicker = null;
    for (Ticker ticker : tickers) {
      for (BuySellCurrency buySellCurrency : ticker.getBuySellCurrencies()) {
        if (buySellCurrency.getCurrency() == Currency.CNY && buySellCurrency.getBuy() > highestBuy) {
          highestBuy = buySellCurrency.getBuy();
          highestBuyTicker = ticker;
        }
      }
    }
    return highestBuyTicker;
  }


  public static Ticker getLowestSell(Ticker... tickers) {
    double lowestSell = Double.MAX_VALUE;
    Ticker lowestSellTicker = null;
    for (Ticker ticker : tickers) {
      for (BuySellCurrency buySellCurrency : ticker.getBuySellCurrencies()) {
        if (buySellCurrency.getCurrency() == Currency.CNY && buySellCurrency.getSell() < lowestSell) {
          lowestSell = buySellCurrency.getSell();
          lowestSellTicker = ticker;
        }
      }
    }
    return lowestSellTicker;
  }

  public static boolean isReachJiaChaThreshold(Ticker highestBuy, Ticker lowestSell, SystemSetting systemSetting) {
    return false;
  }
}
