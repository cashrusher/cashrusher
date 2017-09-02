package com.rusher.yunbi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.Currency;
import com.rusher.utils.JsonMessageMarshaller;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liam on 27/08/2017.
 */
public class YunBiServiceTest {

    private YunBiService service = new YunBiService();

    @Before
    public void setUp() throws Exception {
        service.setYunbiAPI(new YunBiAPI());
        service.setMarshaller(new JsonMessageMarshaller(new ObjectMapper()));
    }

    @Test
    public void getTicker() throws Exception {
        service.getTicker(Currency.ETH);
    }

}