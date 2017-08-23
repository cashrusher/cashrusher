package com.rusher.ws;

public interface WebServiceRequestProcessService<RQ, RS> {
    RS process(RQ request, WebServiceRequestMessage message);
}
