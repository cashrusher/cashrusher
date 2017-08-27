package com.rusher.yunbi.utils;

import com.google.common.collect.Maps;
import com.rusher.Currency;
import com.rusher.yunbi.dto.Symbol;
import com.rusher.yunbi.dto.SymbolPair;

import java.util.Map;

/**
 * Created by liam on 13/08/2017.
 */
public class Constant {
    public static final String URL = "https://yunbi.com";

    public static final Map<Currency, SymbolPair> CurrencyAndSymbolMap = Maps.newConcurrentMap();

    static {
        CurrencyAndSymbolMap.put(Currency.ETH, new SymbolPair(Symbol.eth, Symbol.cny));
        CurrencyAndSymbolMap.put(Currency.BTC, new SymbolPair(Symbol.btc, Symbol.cny));
    }

}
