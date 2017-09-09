package com.rusher.kraken.utils;

import com.google.common.collect.Maps;
import com.rusher.Authorization;
import org.junit.Test;

import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 */
public class HttpUtilsTest {
    private HttpUtils httpUtils = new HttpUtils();
    String secret = "WP90951w5I9uFCLabh8x0SqaKaqeTCe+orIez89Io/68R8i9Xh5lnQeSOsXtlTpf4KJ+ryf8kRMFHyRzuBpfSg==";
    String key = "RNL8qrMdKy+wRwCCR7cm5xHN09Bsew3snZIN3aW3rlnLPvtHTkCKvS+u";

    private Authorization authorization = new Authorization(key, secret);

    @Test
    public void queryPrivate() throws Exception {
        System.out.println(System.currentTimeMillis() * 1000000);
        Map<String, String> params = Maps.newHashMap();
        String url = "/0/private/QueryOrders";
        params.put("txid", "O6IKRT-LFURM-BSI3HG");
        String response = httpUtils.queryPrivate(authorization.getApiKey(), authorization.getSecretKey(), url, params);
        System.out.println(response);
    }

}