package com.rusher.domain.utils;

import com.rusher.client.bitcoin.market.bean.Kline;

/**
 * Created by classic1999 on 14-3-27.
 */
public class MarketUtils {
    public static double avgPrice(Kline kline) {
        return (kline.getOpen() + kline.getClose() + kline.getHigh() + kline.getLow()) / 4;

    }
}
