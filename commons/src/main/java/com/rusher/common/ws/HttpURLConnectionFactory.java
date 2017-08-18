package com.rusher.common.ws;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

public class HttpURLConnectionFactory {
    public static HttpURLConnection create(String url, String method, String contentType, String charset, int connectionTimeout, int readTimeout, HttpAuthorization httpAuthorization, SSLSocketFactory sslSocketFactory) {
        final URLConnection connection = newURLConnection(url);
        if (!(connection instanceof HttpURLConnection)) {
            throw new IllegalArgumentException("URI [" + url + "] is not an HTTP URL");
        }

        final HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        addHttpAuthorization(httpURLConnection, httpAuthorization);
        setRequestMethod(httpURLConnection, method);
        setContentType(httpURLConnection, contentType, charset);
        setTimeout(httpURLConnection, connectionTimeout, readTimeout);
        setSsl(httpURLConnection, sslSocketFactory);
        return httpURLConnection;
    }

    private static void setSsl(HttpURLConnection httpURLConnection, SSLSocketFactory sslSocketFactory) {
        if (httpURLConnection instanceof HttpsURLConnection && sslSocketFactory != null) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sslSocketFactory);
            if (sslSocketFactory instanceof WebServiceSSLSocketAlwaysTrustFactory) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier((s, sslSession) -> true);
            }
        }
    }

    private static void setTimeout(HttpURLConnection httpURLConnection, int connectionTimeout, int readTimeout) {
        httpURLConnection.setConnectTimeout(connectionTimeout);
        httpURLConnection.setReadTimeout(readTimeout);
    }

    private static void setContentType(HttpURLConnection httpURLConnection, String contentType, String charset) {
        httpURLConnection.setRequestProperty(HttpHeaderNames.CONTENT_TYPE, contentType + ";" + HttpHeaderNames.CONTENT_TYPE_CHAR_SET + "=\"" + charset + "\"");
        httpURLConnection.setRequestProperty(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderNames.GZIP);
    }

    private static void addHttpAuthorization(HttpURLConnection httpURLConnection, HttpAuthorization httpAuthorization) {
        if (httpAuthorization != null) {
            httpAuthorization.addToRequestHeader(httpURLConnection);
        }
    }

    private static void setRequestMethod(HttpURLConnection httpURLConnection, String method) {
        try {
            httpURLConnection.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private static URLConnection newURLConnection(String url) {
        try {
            final URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            return urlConnection;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}
