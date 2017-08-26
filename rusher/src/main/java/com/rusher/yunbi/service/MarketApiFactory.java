package com.rusher.yunbi.service;



import com.rusher.yunbi.dto.Market;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarketApiFactory {
    private static MarketApiFactory factory = new MarketApiFactory();

    private MarketApiFactory() {

    }

    public static MarketApiFactory getInstance() {
        return factory;
    }

    static Map<String, AbstractMarketApi> marketMap = new LinkedHashMap<String, AbstractMarketApi>();

    static {
        AbstractMarketApi peatio = new YunBiAPI();
        marketMap.put(peatio.getMarket().name(), peatio);
    }


    public AbstractMarketApi getMarket(String name) {
        AbstractMarketApi market = marketMap.get(name);
        if (market == null) {
            throw new RuntimeException("name:" + name + " is null");
        }
        return market;
    }

    public AbstractMarketApi getMarket(Market market) {
        return getMarket(market.name());
    }


}
