package com.rusher.domain.common.ws;

import com.derbysoft.warrior.common.io.IOUtils;
import com.derbysoft.warrior.common.util.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpURLConnectionHelper {
    public static void addHttpRequestHeader(HttpURLConnection connection, Map<String, String> httpHeader) {
        if (httpHeader != null) {
            for (Map.Entry<String, String> entry : httpHeader.entrySet()) {
                connection.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void send(HttpURLConnection connection, String rqMessage, boolean gzip, String charset) throws IOException {
        if (StringUtils.isNotBlank(rqMessage)) {
            final OutputStream outputStream = connection.getOutputStream();
            if (gzip) {
                connection.setRequestProperty(HttpHeaderNames.CONTENT_ENCODING, HttpHeaderNames.GZIP);
                outputStream.write(GZIPUtils.zip(rqMessage.getBytes(charset)));
            } else {
                outputStream.write(rqMessage.getBytes(charset));
            }
            outputStream.flush();
        }
    }

    public static String read(HttpURLConnection connection, InputStream inputStream, String charset) throws IOException {
        final String rsCharset = HttpHeaderHelper.getCharSetEncodingOfContentType(connection.getHeaderField(HttpHeaderNames.CONTENT_TYPE));
        charset = rsCharset == null ? charset : rsCharset;
        charset = charset == null ? Charsets.UTF8.name() : charset;
        if (HttpHeaderNames.GZIP.equalsIgnoreCase(connection.getHeaderField(HttpHeaderNames.CONTENT_ENCODING))) {
            return new String(IOUtils.readBytes(new GZIPInputStream(inputStream)), charset);
        } else {
            return new String(IOUtils.readBytes(inputStream), charset);
        }
    }
}
