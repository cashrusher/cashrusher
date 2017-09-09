package com.rusher.kraken.service;

import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.KrakenTradeRequest;
import com.rusher.kraken.dto.KrakenTradeResponse;

/**
 * Created by liam on 09/09/2017.
 */
public interface KrakenService {
    public static final String APIURL = "https://api.kraken.com";
    public static String secret = "WP90951w5I9uFCLabh8x0SqaKaqeTCe+orIez89Io/68R8i9Xh5lnQeSOsXtlTpf4KJ+ryf8kRMFHyRzuBpfSg==";
    public static String key = "RNL8qrMdKy+wRwCCR7cm5xHN09Bsew3snZIN3aW3rlnLPvtHTkCKvS+u";


    public KrakenBalance getBalance();

    public KrakenTicker getTicker(String... pairs);

    public KrakenTradeResponse Trade(KrakenTradeRequest request);
}
