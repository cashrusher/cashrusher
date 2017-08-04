package com.rusher.domain.common.ws;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.Charset;

public abstract class HttpResponseHelper {
    public static void writeXml(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, HttpHeaderNames.CONTENT_TYPE_XML, encoding, httpServletResponse);
    }

    public static void writeXmlGZip(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        writeGZip(responseMessage, HttpHeaderNames.CONTENT_TYPE_XML, encoding, httpServletResponse);
    }

    public static void writeJson(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, HttpHeaderNames.CONTENT_TYPE_JSON, encoding, httpServletResponse);
    }

    public static void writeJsonGZip(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        writeGZip(responseMessage, HttpHeaderNames.CONTENT_TYPE_JSON, encoding, httpServletResponse);
    }

    public static void writeHtml(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, HttpHeaderNames.CONTENT_TYPE_HTML, encoding, httpServletResponse);
    }

    public static void writeHtmlGZip(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        writeGZip(responseMessage, HttpHeaderNames.CONTENT_TYPE_HTML, encoding, httpServletResponse);
    }

    public static void writeTxt(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, HttpHeaderNames.CONTENT_TYPE_TXT, encoding, httpServletResponse);
    }

    public static void writeTxtGZip(String responseMessage, Charset encoding, HttpServletResponse httpServletResponse) {
        writeGZip(responseMessage, HttpHeaderNames.CONTENT_TYPE_TXT, encoding, httpServletResponse);
    }

    public static void write(String responseMessage, String contentType, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, contentType, encoding, httpServletResponse, false);
    }

    public static void writeGZip(String responseMessage, String contentType, Charset encoding, HttpServletResponse httpServletResponse) {
        write(responseMessage, contentType, encoding, httpServletResponse, true);
    }

    private static void write(String responseMessage, String contentType, Charset encoding, HttpServletResponse httpServletResponse, boolean gzip) {
        try {
            encoding = encoding == null ? Charsets.UTF8 : encoding;
            httpServletResponse.setHeader(HttpHeaderNames.CONTENT_TYPE, contentType + ";" + HttpHeaderNames.CONTENT_TYPE_CHAR_SET + "=" + encoding.name());

            byte[] responseBytes = responseMessage.getBytes(encoding);
            if (gzip) {
                httpServletResponse.setHeader(HttpHeaderNames.CONTENT_ENCODING, HttpHeaderNames.GZIP);
                responseBytes = GZIPUtils.zip(responseBytes);
            }

            final OutputStream os = httpServletResponse.getOutputStream();
            try {
                os.write(responseBytes);
            } finally {
                os.flush();
            }
        } catch (Exception e) {
            throw new HttpResponseWriteException(e);
        }
    }
}
