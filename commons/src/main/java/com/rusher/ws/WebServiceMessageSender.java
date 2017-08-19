package com.rusher.ws;

import java.util.Map;

public interface WebServiceMessageSender {
    String send(String url);

    String send(String url, String rqMessage);

    String send(String url, String rqMessage, Map<String, String> httpHeader);
}
