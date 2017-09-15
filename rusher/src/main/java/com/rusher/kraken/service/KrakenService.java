package com.rusher.kraken.service;

import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.interfaces.dto.Ticker;
import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.KrakenTradeRequest;
import com.rusher.kraken.dto.KrakenTradeResponse;

/**
 * Created by liam on 09/09/2017.
 */
public interface KrakenService {

  KrakenBalance getBalance(Authorization authorization);

  Ticker getTicker(Currency currency);

  KrakenTicker getTicker(String... pairs);

  KrakenTradeResponse Trade(Authorization authorization, KrakenTradeRequest request);
}
