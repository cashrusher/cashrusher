package com.rusher.okcoin.service;

import com.rusher.Authorization;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by liam on 26/08/2017.
 */
public class OKCoinServiceTest {

    private Authorization authorization;

    @Before
    public void setUp() throws Exception {
        authorization = new Authorization("14d0881c-68b8-4de7-8ef5-b2140ba2780c", "0440198DB0B9D02BBF0F240AB220208A");
    }

    @Test
    public void getAssert() throws Exception {
        OKCoinService okCoinService=new OKCoinService();
//        okCoinService.getAssert(authorization);
    }

}