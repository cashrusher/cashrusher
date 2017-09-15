package com.rusher.kraken.service;

import com.google.common.collect.Maps;
import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.domain.common.translator.TranslatorTri;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.kraken.dto.*;
import com.rusher.utils.JsonMessageMarshaller;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.rusher.kraken.utils.HttpUtils.queryPrivate;
import static com.rusher.kraken.utils.HttpUtils.queryPublic;


/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service
public class KrakenServiceImpl implements KrakenService {
  @Autowired
  private JsonMessageMarshaller marshaller;

  @Autowired
  @Qualifier("krakenTicker2TickerTranslator")
  private TranslatorTri<KrakenTicker, SystemSetting, Pairs, Ticker> krakenTicker2TickerTranslator;

  @Override
  public KrakenBalance getBalance(Authorization authorization) {
    final String url = "/0/private/Balance";
    Map<String, String> params = Maps.newHashMap();
    try {
      String response = queryPrivate(authorization.getApiKey(), authorization.getSecretKey(), url, params);
      System.out.println(response);
      return (KrakenBalance) marshaller.doUnmarshal(response, KrakenBalance.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Ticker getTicker(Currency currency) {

    return null;
  }

  @Override
  public KrakenTicker getTicker(String... pairs) {
    final String url = "/0/public/Ticker";
    Map<String, String> params = Maps.newHashMap();
    params.put("pair", StringUtils.join(pairs, ","));
    System.out.println(params.get("pair"));
    try {
      String response = queryPublic(url, params);
      System.out.println(response);
      return (KrakenTicker) marshaller.doUnmarshal(response, KrakenTicker.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public KrakenTradeResponse Trade(Authorization authorization, KrakenTradeRequest request) {
    final String url = "/0/private/AddOrder";
    Map<String, String> params = Maps.newHashMap();
    params.put("pair", request.getPair());
    params.put("type", request.getType().name());
    params.put("ordertype", request.getOrderType().getOrderType());
    if (request.getVolume() != null) {
      params.put("volume", String.valueOf(request.getVolume()));
    }
    if (request.getPrice() != null) {
      params.put("price", String.valueOf(request.getPrice()));
    }
    if (request.getPrice2() != null) {
      params.put("price2", String.valueOf(request.getPrice2()));
    }
    if (request.getLeverage() != null) {
      params.put("leverage", String.valueOf(request.getLeverage()));
    }
    if (request.getoFlags() != null) {
      params.put("oflags", request.getoFlags().name());
    }
    if (request.getStarttm() != null) {
      params.put("starttm", String.valueOf(request.getStarttm()));
    }
    if (request.getExpiretm() != null) {
      params.put("expiretm", String.valueOf(request.getExpiretm()));
    }
    if (request.getValidate() != null) {
      params.put("validate", request.getValidate());
    }
//      params.put("close_order_type", request.());
//      params.put("close_price", request.getPrice());
//      params.put("close_price2", request.getClosePrice2());
//      params.put("trading_agreement", request.getTradingAgreement());
    try {
      String response = queryPrivate(authorization.getApiKey(), authorization.getSecretKey(), url, params);
      System.out.println(response);
      return (KrakenTradeResponse) marshaller.doUnmarshal(response, KrakenTradeResponse.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }


  public void setMarshaller(JsonMessageMarshaller marshaller) {
    this.marshaller = marshaller;
  }
}
