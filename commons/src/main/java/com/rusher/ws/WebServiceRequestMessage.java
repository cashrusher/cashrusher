package com.rusher.ws;

import java.nio.charset.Charset;
import java.util.Date;

public class WebServiceRequestMessage {
    private final Date requestedDateTime = new Date();
    private final String uri;
    private final String contentType;
    private Charset charset;
    private boolean gzip;
    private byte[] data;

    public WebServiceRequestMessage(String uri, String contentType, Charset charset, boolean gzip, byte[] data) {
        this.uri = uri;
        this.contentType = contentType;
        this.charset = charset;
        this.gzip = gzip;
        this.data = data;
    }

    public Date getRequestedDateTime() {
        return requestedDateTime;
    }

    public String getUri() {
        return uri;
    }

    public String getContentType() {
        return contentType;
    }

    public Charset getCharset() {
        return charset;
    }

    public boolean isGzip() {
        return gzip;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getGZipData() {
        return gzip ? data : GZIPUtils.zip(data);
    }
}
