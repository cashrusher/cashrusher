package com.rusher.kraken.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 */
public class SignatureTest {
    @Test
    public void createSignature() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("nonce", "123");
        String sign = Signature.createSignature("1", params, "1".getBytes());
        String expect="UKszwkonYhwkibihsUIcVI25nSrJwKo0loW7H2u/NPFmp4jGwsEjdVxNrHgDTNsQ4pdQJbrK+D3Hsq94/yyRYg==";
        Assert.assertEquals(expect,sign);
        System.out.println(sign);
    }

}