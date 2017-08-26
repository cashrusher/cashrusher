package com.rusher.interfaces.ws.service;

import com.rusher.interfaces.dto.AssertRequest;
import com.rusher.kraken.service.KrakenService;
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
@Service("assertProcessService")
public class AssertProcessService implements WebServiceRequestProcessService<AssertRequest, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");
    @Autowired
    private KrakenService krakenService;

    @Override
    public Object process(AssertRequest request, WebServiceRequestMessage message) {

        return null;
    }
}
