package com.rusher.bitfinex.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.Authorization;
import com.rusher.bitfinex.dto.BitfinexBalance;
import com.rusher.bitfinex.dto.BitfinexTicker;
import com.rusher.bitfinex.dto.Symbols;
import com.rusher.kraken.utils.Signature;
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

//	      key:="eRNLi8wYH0SXncyDUkfmWs99CVvjqSUnQ6KcBqnwhDt"
//        secret:="4qaFdWxqA6whubyPI92DTWSnDKbiABuMP7CnixmM2Vb"
        String key = "eRNLi8wYH0SXncyDUkfmWs99CVvjqSUnQ6KcBqnwhDt";
        String secret = "4qaFdWxqA6whubyPI92DTWSnDKbiABuMP7CnixmM2Vb";
        bitfinexService.setAuthorization(new Authorization(key, secret));
        bitfinexService.setMarshaller(new JsonMessageMarshaller(new ObjectMapper()));
    }

    @Test
    public void getBalance() throws Exception {
        byte[] b1 = "1".getBytes();
        byte[] b2 = "2".getBytes();
        String signature = Signature.getHMacSha384(b1, b2);
        System.out.println(signature);
        BitfinexBalance balance=bitfinexService.getBalance();
    }

    @Test
    public void getTicker() throws Exception {
        List<BitfinexTicker> tickers = bitfinexService.getTicker(Symbols.ETHUSD);
    }

    @Test
    public void trade() throws Exception {

    }

}