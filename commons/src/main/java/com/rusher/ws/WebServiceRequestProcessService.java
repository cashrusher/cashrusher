package com.rusher.ws;

public interface WebServiceRequestProcessService<RQ, RS> {
    RS processPost(RQ request, WebServiceRequestMessage message);

    RS processGet();
}
