package com.rusher.bitfinex.service;


import com.rusher.Authorization;
import com.rusher.bitfinex.dto.BitfinexBalance;
import com.rusher.bitfinex.dto.BitfinexTicker;
import com.rusher.bitfinex.dto.BitfinexTradeRequest;
import com.rusher.bitfinex.dto.BitfinexTradeResponse;

import java.util.List;

/**
 * Created by liam on 10/09/2017.
 */
public interface BitfinexService {
  String APIURL = "https://api.bitfinex.com";

  BitfinexBalance getBalance(Authorization authorization) throws RuntimeException;

  List<BitfinexTicker> getTicker(String... pairs);

  BitfinexTradeResponse Trade(Authorization authorization, BitfinexTradeRequest request);
}
