package com.rusher.okcoin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.okcoin.dto.OKCoinTicker;
import com.rusher.utils.JsonMessageMarshaller;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liam on 26/08/2017.
 */
public class OKCoinServiceTest {

    private Authorization authorization;
    private JsonMessageMarshaller marshaller = new JsonMessageMarshaller(new ObjectMapper());
    private OKCoinService service = new OKCoinService();

    @Before
    public void setUp() throws Exception {
        authorization = new Authorization("14d0881c-68b8-4de7-8ef5-b2140ba2780c", "0440198DB0B9D02BBF0F240AB220208A");
        service.setMarshaller(marshaller);
    }

    @Test
    public void getAsset() throws Exception {
        OKCoinService okCoinService = new OKCoinService();
//        okCoinService.getAsset(authorization);
    }

    @Test
    public void getTicker() throws Exception {
        OKCoinTicker ticker = service.getTicker(Currency.ETH);

    }

}