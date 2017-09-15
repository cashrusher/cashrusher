package com.rusher.interfaces.ws.endpoint;

import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 19/08/2017.
 * To Fetch data
 */
@Service("thresholdTriggerProcessService")
public class ThresholdTriggerProcessService implements WebServiceRequestProcessService<String, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");


    @Override
    public Object processPost(String request, WebServiceRequestMessage message) {

        return null;
    }

    @Override
    public Object processGet() {

        return null;
    }
}
