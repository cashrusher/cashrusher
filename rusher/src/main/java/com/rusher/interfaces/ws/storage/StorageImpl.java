package com.rusher.interfaces.ws.storage;

import com.google.common.collect.Maps;
import com.rusher.Platform;
import com.rusher.interfaces.dto.Ticker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liam on 14/09/2017.
 */
public class StorageImpl implements Storage {

    private final int MAX_SIZE_HISTORY = 100;
    //To store the tickers based on the platform
    //LinkedList is time related.
    private List<Map<Platform, Ticker>> tickers = Collections.synchronizedList(new LinkedList<Map<Platform, Ticker>>());

    @Override
    public void save(List allTickers) {
        if (tickers.size() > MAX_SIZE_HISTORY) {
            tickers.remove(MAX_SIZE_HISTORY - 1);
        }
        Map<Platform, Ticker> platformTickerMap = Maps.newConcurrentMap();
        for (Ticker ticker : (List<Ticker>) allTickers) {
            platformTickerMap.put(ticker.getPlatform(), ticker);
        }
        tickers.add(platformTickerMap);
    }

    @Override
    public Object get() {
        return null;
    }

}
