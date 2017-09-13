package com.rusher.kraken.utils;

import com.google.common.collect.Maps;
import com.rusher.Currency;
import com.rusher.kraken.dto.Pairs;

import java.util.Map;

/**
 * Created by liam on 13/08/2017.
 */
public class Constant {
    public static final String APIURL = "https://api.kraken.com";

    public static final Map<Currency, String> CurrencyAndSymbolMap = Maps.newConcurrentMap();

    static {
        CurrencyAndSymbolMap.put(Currency.ETH, Pairs.XETHZUSD);
        CurrencyAndSymbolMap.put(Currency.BTC, Pairs.XETCXUSD);
    }

}
