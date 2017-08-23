package com.rusher.http;

import com.rusher.ws.HttpHeaderNames;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

public abstract class HttpHeaderHelper {
    public static boolean hasContentLength(HttpServletRequest httpServletRequest) {
        return StringUtils.isNotBlank(httpServletRequest.getHeader(HttpHeaderNames.CONTENT_LENGTH));
    }

    public static int getContentLength(HttpServletRequest httpServletRequest) {
        final String length = httpServletRequest.getHeader(HttpHeaderNames.CONTENT_LENGTH);
        if (StringUtils.isBlank(length)) {
            throw new RuntimeException("No " + HttpHeaderNames.CONTENT_LENGTH + " in the Http Head");
        }

        try {
            return Integer.parseInt(length.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid " + HttpHeaderNames.CONTENT_LENGTH + ":" + length + " in the Http Head");
        }
    }

    public static String getContentType(HttpServletRequest httpServletRequest) {
        final String contentType = httpServletRequest.getHeader(HttpHeaderNames.CONTENT_TYPE);
        if (contentType == null) {
            return null;
        }

        for (String v : contentType.split(";")) {
            v = v.trim();
            if (v.indexOf('/') > 0 && v.indexOf('=') < 0) {
                return v;
            }
        }
        return null;
    }

    public static Charset getCharset(HttpServletRequest httpServletRequest) {
        return getCharset(httpServletRequest, null);
    }

    public static Charset getCharset(HttpServletRequest httpServletRequest, Charset defaultCharset) {
        try {
            final String encoding = getCharSetEncodingOfContentType(httpServletRequest.getHeader(HttpHeaderNames.CONTENT_TYPE));
            return encoding == null ? defaultCharset : Charset.forName(encoding);
        } catch (Exception e) {
            return defaultCharset;
        }
    }

    public static String getCharSetEncodingOfContentType(String contentType) {
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

    public static boolean isGZipContent(HttpServletRequest httpServletRequest) {
        return hasGzip(httpServletRequest.getHeaders(HttpHeaderNames.CONTENT_ENCODING));
    }

    public static boolean isAcceptGZip(HttpServletRequest httpServletRequest) {
        return hasGzip(httpServletRequest.getHeaders(HttpHeaderNames.ACCEPT_ENCODING));
    }

    private static boolean hasGzip(Enumeration contentEncodings) {
        if (contentEncodings == null) {
            return false;
        }

        if (contentEncodings.hasMoreElements()) {
            if (contentEncodings.nextElement().toString().toLowerCase().contains(HttpHeaderNames.GZIP)) {
                return true;
            }
        }
        return false;
    }
}
