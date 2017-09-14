package com.rusher.interfaces.ws.support;

import com.rusher.interfaces.dto.Ticker;
import com.rusher.interfaces.model.db.SystemSetting;

/**
 * Created by liam on 14/09/2017.
 */
public class AlgorithmSupportJiaCha {

    public static Ticker getHighestBuy(Ticker... tickers) {
        double highestBuy = 0d;
        Ticker highestBuyTicker = null;
        for (Ticker ticker : tickers) {
            if (ticker.getBuy() > highestBuy) {
                highestBuy = ticker.getBuy();
                highestBuyTicker = ticker;
            }
        }
        return highestBuyTicker;
    }

    public static Ticker getLowestSell(Ticker... tickers) {
        double lowestSell = Double.MAX_VALUE;
        Ticker lowestSellTicker = null;
        for (Ticker ticker : tickers) {
            if (ticker.getSell() < lowestSell) {
                lowestSell = ticker.getSell();
                lowestSellTicker = ticker;
            }
        }
        return lowestSellTicker;
    }

    public static boolean isReachJiaChaThreshold(Ticker highestBuy, Ticker lowestSell, SystemSetting systemSetting) {
        return false;
    }
}
