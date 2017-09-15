package com.rusher.kraken.translator;

import com.google.common.collect.Lists;
import com.rusher.Currency;
import com.rusher.Platform;
import com.rusher.domain.common.translator.TranslatorTri;
import com.rusher.interfaces.dto.BuySellCurrency;
import com.rusher.interfaces.dto.Error;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.interfaces.ws.exception.UnsupportCurrencyException;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.Pairs;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: Liam
 * Date: 2017/9/14
 *
 * The default Currency of Kraken is USD
 */
@Component("krakenTicker2TickerTranslator")
public class KrakenTicker2TickerTranslator implements TranslatorTri<KrakenTicker, SystemSetting, Pairs, Ticker> {

  @Override
  public Ticker translate(KrakenTicker source, SystemSetting systemSetting, Pairs krakenPair) {
    Ticker ticker = new Ticker();
    if (source.getError() != null) {
      ticker.setError(createError(source.getError()));
      return ticker;
    }
    ticker.setDate(new Date());
    ticker.setPlatform(Platform.KRAKEN);
    ticker.setBuySellCurrencies(createBuySellCurrencies(source, systemSetting, krakenPair));
    return ticker;
  }

  private Error createError(List<Map<String, String>> errors) {
    Error err = new Error();
    String code = "";
    String msg = "";
    for (Map<String, String> error : errors) {
      for (String key : error.keySet()) {
        code += key + ",";
        msg += error.get(key) + ",";
      }
    }
    err.setCode(code.substring(0, code.length() - 1));
    err.setMessage(msg.substring(0, msg.length() - 1));
    return err;
  }

  private List<BuySellCurrency> createBuySellCurrencies(KrakenTicker source, SystemSetting systemSetting, Pairs krakenPair) {
    List<BuySellCurrency> buySellCurrencies = Lists.newArrayList();
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), krakenPair, Currency.USD));
    buySellCurrencies.add(createBuySellCurrency(source, systemSetting.getCnyusd(), krakenPair, Currency.CNY));
    return buySellCurrencies;
  }

  private BuySellCurrency createBuySellCurrency(KrakenTicker source, double CNY2USDRatio, Pairs krakenPair, Currency currency) {
    BuySellCurrency buySellCurrency = new BuySellCurrency();
    switch (currency) {
      case CNY:
        buySellCurrency.setBuy(CNY2USDRatio * source.getResult().get(krakenPair).getBid().get(0));
        buySellCurrency.setSell(CNY2USDRatio * source.getResult().get(krakenPair).getAsk().get(0));
        buySellCurrency.setCurrency(Currency.CNY);
        return buySellCurrency;
      case USD:
        buySellCurrency.setBuy(source.getResult().get(krakenPair).getBid().get(0));
        buySellCurrency.setSell(source.getResult().get(krakenPair).getAsk().get(0));
        buySellCurrency.setCurrency(Currency.CNY);
        return buySellCurrency;
      default:
        throw new UnsupportCurrencyException("Only support CNY and USD, do not support: " + currency);
    }
  }
}
