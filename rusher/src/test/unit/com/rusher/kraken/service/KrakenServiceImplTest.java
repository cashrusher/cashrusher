package com.rusher.kraken.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.kraken.dto.KrakenBalance;
import com.rusher.kraken.dto.KrakenTicker;
import com.rusher.kraken.dto.Pairs;
import com.rusher.utils.JsonMessageMarshaller;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liam on 09/09/2017.
 */
public class KrakenServiceImplTest {
    private KrakenServiceImpl service = new KrakenServiceImpl();
    private JsonMessageMarshaller marshaller = new JsonMessageMarshaller(new ObjectMapper());

    @Before
    public void setUp() throws Exception {
        service.setMarshaller(marshaller);
    }


    @Test
    public void getTicker() throws Exception {
        KrakenTicker ticker = service.getTicker(Pairs.XETHZUSD);
        System.out.println(marshaller.marshal(ticker));
    }

    @Test
    public void trade() throws Exception {

    }

    @Test
    public void getBalance() throws Exception {
        KrakenBalance balance = service.getBalance();
        System.out.println(marshaller.marshal(balance));
    }

}