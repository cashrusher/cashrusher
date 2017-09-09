package com.rusher.kraken.utils;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 */
public class HttpUtilsTest {
    private HttpUtils httpUtils = new HttpUtils();

    @Test
    public void queryPrivate() throws Exception {
        //1504941026702759672
        //1504941038945000000
        System.out.println(System.currentTimeMillis() * 1000000);
        Map<String, String> params = Maps.newHashMap();
        String url = "/0/private/QueryOrders";
        params.put("txid","1");
        String response = httpUtils.queryPrivate(url, params);
        System.out.println(response);
    }

}