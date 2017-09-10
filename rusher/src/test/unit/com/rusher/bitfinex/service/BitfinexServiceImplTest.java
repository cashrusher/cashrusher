package com.rusher.bitfinex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.bitfinex.dto.BitfinexTicker;
import com.rusher.bitfinex.dto.Symbols;
import com.rusher.utils.JsonMessageMarshaller;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by liam on 10/09/2017.
 */
public class BitfinexServiceImplTest {

    private BitfinexServiceImpl bitfinexService = new BitfinexServiceImpl();


    @Before
    public void setUp() throws Exception {
        bitfinexService.setMarshaller(new JsonMessageMarshaller(new ObjectMapper()));
    }

    @Test
    public void getBalance() throws Exception {

    }

    @Test
    public void getTicker() throws Exception {
       List<BitfinexTicker> tickers= bitfinexService.getTicker(Symbols.ETHUSD);
    }

    @Test
    public void trade() throws Exception {

    }

}