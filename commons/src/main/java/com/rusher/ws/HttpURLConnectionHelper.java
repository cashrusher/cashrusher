package com.rusher.ws;

import com.rusher.Charsets;
import com.rusher.IOUtils;
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
        final String rsCharset = getCharSetEncodingOfContentType(connection.getHeaderField(HttpHeaderNames.CONTENT_TYPE));
        charset = rsCharset == null ? charset : rsCharset;
        charset = charset == null ? Charsets.UTF8.name() : charset;
        if (HttpHeaderNames.GZIP.equalsIgnoreCase(connection.getHeaderField(HttpHeaderNames.CONTENT_ENCODING))) {
            return new String(IOUtils.readBytes(new GZIPInputStream(inputStream)), charset);
        } else {
            return new String(IOUtils.readBytes(inputStream), charset);
        }
    }

    private static String getCharSetEncodingOfContentType(String contentType) {
        if (contentType == null) {
            return null;
        }

        int index = contentType.indexOf(HttpHeaderNames.CONTENT_TYPE_CHAR_SET);
        if (index == -1) {
            return null;
        }

        int idx = contentType.indexOf('=', index);
        int indexOfSemiColon = contentType.indexOf(';', idx);

        String value;
        if (indexOfSemiColon > 0) {
            value = contentType.substring(idx + 1, indexOfSemiColon);
        } else {
            value = contentType.substring(idx + 1, contentType.length()).trim();
        }

        if (value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"') {
            return value.substring(1, value.length() - 1);
        }

        if ("null".equalsIgnoreCase(value)) {
            return null;
        } else {
            return value.trim();
        }
    }
}
