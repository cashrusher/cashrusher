package com.rusher.domain.utils;


import com.rusher.domain.protocol.Kline;

/**
 * Created by classic1999 on 14-3-27.
 */
public class MarketUtils {
    public static double avgPrice(Kline kline) {
        return (kline.getOpen() + kline.getClose() + kline.getHigh() + kline.getLow()) / 4;

    }
}
