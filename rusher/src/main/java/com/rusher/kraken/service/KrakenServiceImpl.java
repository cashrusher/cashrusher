package com.rusher.kraken.service;

import com.google.common.collect.Maps;
import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.KrakenTradeRequest;
import com.rusher.utils.JsonMessageMarshaller;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.rusher.kraken.utils.HttpUtils.queryPrivate;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service
public class KrakenServiceImpl implements KrakenService {
    @Autowired
    private JsonMessageMarshaller marshaller;

    @Override
    public KrakenBalance getBalance() {
        final String url = "/0/private/Balance";
        Map<String, String> params = Maps.newHashMap();
        try {
            String response = queryPrivate(url, params);
            System.out.println(response);
            return (KrakenBalance) marshaller.doUnmarshal(response,KrakenBalance.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public KrakenTicker getTicker(String ...pairs) {
        final String url="/0/public/Ticker";
        Map<String, String> params = Maps.newHashMap();
        params.put("pair", StringUtils.join(pairs,","));
        System.out.println(params.get("pair"));
        try {
            String response = queryPrivate(url, params);
            System.out.println(response);
            return (KrakenTicker) marshaller.doUnmarshal(response,KrakenTicker.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void Trade(KrakenTradeRequest request) {

    }


    public void setMarshaller(JsonMessageMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
