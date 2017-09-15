package com.rusher.yunbi.translator;

import com.google.common.collect.Lists;
import com.rusher.Currency;
import com.rusher.Platform;
import com.rusher.domain.common.translator.TranslatorEx;
import com.rusher.interfaces.dto.BuySellCurrency;
import com.rusher.interfaces.dto.Error;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.Constant;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.interfaces.ws.exception.UnsupportCurrencyException;
import com.rusher.yunbi.dto.YunBiTicker;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Author: Liam
 * Date: 2017/9/14
 *
 * The default currency of Yunbi is CNY.
 */
@Component("yunBiTicker2Ticker")
public class YunBiTicker2Ticker implements TranslatorEx<YunBiTicker, SystemSetting, Ticker> {

  @Override
  public Ticker translate(YunBiTicker source, SystemSetting systemSetting) {
    Ticker ticker = new Ticker();
    if (source == null) {
      ticker.setError(new Error(Constant.SYSTEM, "Can not get YunBi Ticker"));
      return ticker;
    }
    ticker.setDate(new Date());
    ticker.setPlatform(Platform.YUNBI);
    ticker.setBuySellCurrencies(createBuySellCurrencies(source, systemSetting));
    return ticker;
  }

  private List<BuySellCurrency> createBuySellCurrencies(YunBiTicker source, SystemSetting systemSetting) {
    List<BuySellCurrency> buySellCurrencies = Lists.newArrayList();
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), Currency.USD));
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), Currency.CNY));
    return buySellCurrencies;
  }

  private BuySellCurrency createBuySellCurrency(YunBiTicker source, double CNY2USDRatio, Currency currency) {
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
