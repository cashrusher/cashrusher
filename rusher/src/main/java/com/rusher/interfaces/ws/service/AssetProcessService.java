package com.rusher.interfaces.ws.service;

import com.rusher.interfaces.dto.AssertRequest;
import com.rusher.kraken.service.KrakenService;
import com.rusher.okcoin.Authorization;
import com.rusher.okcoin.dto.OKCoinAssert;
import com.rusher.okcoin.service.OKCoinService;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("assetProcessService")
public class AssetProcessService implements WebServiceRequestProcessService<AssertRequest, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");
    @Autowired
    private KrakenService krakenService;

    @Autowired
    private OKCoinService okCoinService;

    @Override
    public Object process(AssertRequest request, WebServiceRequestMessage message) {
        OKCoinAssert okCoinAssert = okCoinService.getAssert(new Authorization("14d0881c-68b8-4de7-8ef5-b2140ba2780c", "0440198DB0B9D02BBF0F240AB220208A"));

        return null;
    }

    @Override
    public Object process() {
        OKCoinAssert okCoinAssert = okCoinService.getAssert(new Authorization("14d0881c-68b8-4de7-8ef5-b2140ba2780c", "0440198DB0B9D02BBF0F240AB220208A"));
        return okCoinAssert;
    }
}
