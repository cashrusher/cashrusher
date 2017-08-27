package com.rusher.okcoin;

import com.google.common.collect.Maps;
import com.rusher.Currency;

import java.util.Map;

import static com.rusher.Currency.BTC;
import static com.rusher.Currency.ETH;

/**
 * Created by liam on 26/08/2017.
 */
public class constant {
    public static final String OKCOIN_URL = "https://www.okcoin.cn";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn
    public static final Map<Currency, String> currencyAndSymbolMap = Maps.newConcurrentMap();

    static {
        currencyAndSymbolMap.put(ETH,"eth_cny");
        currencyAndSymbolMap.put(BTC,"btc_cny");
    }
}
