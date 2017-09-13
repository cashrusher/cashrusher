package com.rusher.kraken.service;

import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.KrakenTradeRequest;
import com.rusher.kraken.dto.KrakenTradeResponse;

/**
 * Created by liam on 09/09/2017.
 */
public interface KrakenService {

    KrakenBalance getBalance();

    KrakenTicker getTicker(String... pairs);

    KrakenTradeResponse Trade(KrakenTradeRequest request);
}
