package com.rusher.ws;


import com.rusher.Charsets;

public class WebServiceRequestMessageHelper {
    public static String toString(WebServiceRequestMessage message) {
        if (message.isGzip()) {
            return GZIPUtils.unzipToString(message.getData(), message.getCharset() == null ? Charsets.UTF8 : message.getCharset());
        } else {
            return new String(message.getData(), message.getCharset() == null ? Charsets.UTF8 : message.getCharset());
        }
    }
}
