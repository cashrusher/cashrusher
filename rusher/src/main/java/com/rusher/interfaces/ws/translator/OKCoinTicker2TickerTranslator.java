package com.rusher.interfaces.ws.translator;

import com.google.common.collect.Lists;
import com.rusher.Currency;
import com.rusher.Platform;
import com.rusher.interfaces.dto.BuySellCurrency;
import com.rusher.interfaces.dto.Error;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.Constant;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.interfaces.ws.exception.UnsupportCurrencyException;
import com.rusher.okcoin.dto.OKCoinTicker;

import java.util.Date;
import java.util.List;

/**
 * Author: Liam
 * Date: 2017/9/14
 * <p>
 * The default currency for OKCoin is CNY
 */
public class OKCoinTicker2TickerTranslator implements TranslatorEx<OKCoinTicker, SystemSetting, Ticker> {

  @Override
  public Ticker translate(OKCoinTicker source, SystemSetting systemSetting) {
    Ticker ticker = new Ticker();
    if (source == null) {
      ticker.setError(new Error(Constant.SYSTEM, "Can not get OKCoin Ticker"));
      return ticker;
    }
    ticker.setDate(new Date());
    ticker.setPlatform(Platform.OKCOIN);
    ticker.setBuySellCurrencies(createBuySellCurrencies(source, systemSetting));
    return ticker;
  }

  private List<BuySellCurrency> createBuySellCurrencies(OKCoinTicker source, SystemSetting systemSetting) {
    List<BuySellCurrency> buySellCurrencies = Lists.newArrayList();
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), Currency.USD));
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), Currency.CNY));
    return buySellCurrencies;
  }

  private BuySellCurrency createBuySellCurrency(OKCoinTicker source, double CNY2USDRatio, Currency currency) {
    BuySellCurrency buySellCurrency = new BuySellCurrency();
    switch (currency) {
      case CNY:
        buySellCurrency.setBuy(source.getTicker().getBuy());
        buySellCurrency.setSell(source.getTicker().getSell());
        buySellCurrency.setCurrency(Currency.CNY);
        return buySellCurrency;
      case USD:
        buySellCurrency.setBuy(source.getTicker().getBuy() / CNY2USDRatio);
        buySellCurrency.setSell(source.getTicker().getSell() / CNY2USDRatio);
        buySellCurrency.setCurrency(Currency.CNY);
        return buySellCurrency;
      default:
        throw new UnsupportCurrencyException("Only support CNY and USD, do not support: " + currency);
    }
  }
}
