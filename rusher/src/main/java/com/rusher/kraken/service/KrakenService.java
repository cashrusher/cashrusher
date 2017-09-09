package com.rusher.kraken.service;

import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.KrakenTradeRequest;

/**
 * Created by liam on 09/09/2017.
 */
public interface KrakenService {
    public KrakenBalance getBalance();

    public KrakenTicker getTicker(String ...pairs);

    public void Trade(KrakenTradeRequest request);
}
