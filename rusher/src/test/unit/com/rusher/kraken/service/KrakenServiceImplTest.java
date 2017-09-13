package com.rusher.kraken.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.Authorization;
import com.rusher.kraken.dto.*;
import com.rusher.utils.JsonMessageMarshaller;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liam on 09/09/2017.
 */
public class KrakenServiceImplTest {
    private KrakenServiceImpl service = new KrakenServiceImpl();
    private JsonMessageMarshaller marshaller = new JsonMessageMarshaller(new ObjectMapper());

    private Authorization authorization=new Authorization("RNL8qrMdKy+wRwCCR7cm5xHN09Bsew3snZIN3aW3rlnLPvtHTkCKvS+u","WP90951w5I9uFCLabh8x0SqaKaqeTCe+orIez89Io/68R8i9Xh5lnQeSOsXtlTpf4KJ+ryf8kRMFHyRzuBpfSg==");

    @Before
    public void setUp() throws Exception {
        service.setMarshaller(marshaller);
    }


    @Test
    public void getTicker() throws Exception {
        KrakenTicker ticker = service.getTicker(Pairs.XETHZUSD);
        System.out.println(marshaller.marshal(ticker));
    }

    //这个测试每次会进行0.02ETH的卖出和买入
    @Test
    public void trade() throws Exception {
//        KrakenTradeRequest request = createRequest(Type.sell);
//        KrakenTradeResponse response = service.Trade(request);
//        System.out.println(marshaller.marshal(response));
//
//        request = createRequest(Type.buy);
//        response = service.Trade(request);
//        System.out.println(marshaller.marshal(response));
    }

    private KrakenTradeRequest createRequest(Type type) {
        KrakenTradeRequest request = new KrakenTradeRequest();
        request.setPair(Pairs.XETHZUSD);
        request.setType(type);
        request.setOrderType(OrderType.MARKET);
        request.setVolume(0.02d);
        return request;
    }

    @Test
    public void getBalance() throws Exception {
        KrakenBalance balance = service.getBalance();
        System.out.println(marshaller.marshal(balance));
    }

}