package com.rusher.domain.common.ws;
//
//import org.apache.commons.lang3.StringUtils;
//
//import javax.net.ssl.SSLSocketFactory;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.util.Map;
//
public abstract class WebServiceMessageHttpSender implements WebServiceMessageSender {

//    private final String contentType;
//    private final int connectionTimeout;
//    private final int readTimeout;
//    private final SSLSocketFactory sslSocketFactory;
//    protected final String charset;
//    private boolean requestGzip = false;
//    private HttpAuthorization httpAuthorization;
//    private boolean throwHttpError = false;
//    private final ThreadLocal<Integer> responseCode = new ThreadLocal<>();
//    private TimeoutMonitor<HttpURLConnection> httpURLConnectionTimeoutMonitor;
//
//    public WebServiceMessageHttpSender(String contentType, int connectionTimeout, int readTimeout) {
//        this(contentType, Charsets.UTF8.name(), connectionTimeout, readTimeout, null);
//    }
//
//    public WebServiceMessageHttpSender(String contentType, String charset, int connectionTimeout, int readTimeout) {
//        this(contentType, charset, connectionTimeout, readTimeout, null);
//    }
//
//    public WebServiceMessageHttpSender(String contentType, String charset, int connectionTimeout, int readTimeout, SSLSocketFactory sslSocketFactory) {
//        this.contentType = contentType;
//        this.charset = charset;
//        this.connectionTimeout = connectionTimeout;
//        this.readTimeout = readTimeout;
//        this.sslSocketFactory = sslSocketFactory;
//    }
//
//    public void setThrowHttpError(boolean throwHttpError) {
//        this.throwHttpError = throwHttpError;
//    }
//
//    public void setRequestGzip(boolean requestGzip) {
//        this.requestGzip = requestGzip;
//    }
//
////    public void setHttpAuthorization(HttpAuthorization httpAuthorization) {
////        this.httpAuthorization = httpAuthorization;
////    }
////
////    public synchronized void setForcedReleaseTimeoutMinute(long forcedReleaseTimeoutMinute) {
////        if (forcedReleaseTimeoutMinute < 1) {
////            if (httpURLConnectionTimeoutMonitor != null) {
////                httpURLConnectionTimeoutMonitor.close();
////                httpURLConnectionTimeoutMonitor = null;
////            }
////        } else {
////            httpURLConnectionTimeoutMonitor = new TimeoutMonitor<>(forcedReleaseTimeoutMinute, HttpURLConnection::disconnect);
////        }
////    }
////
////    @Override
////    public String send(String url) {
////        return send(url, null, null);
////    }
////
////    @Override
////    public String send(String url, String rqMessage) {
////        return send(url, rqMessage, null);
////    }
//
////    @Override
////    public String send(String url, String rqMessage, Map<String, String> httpHeader) {
////        responseCode.remove();
////        final HttpURLConnection connection = HttpURLConnectionFactory.create(url, getRequestMethod(), contentType, charset, connectionTimeout, readTimeout, httpAuthorization, sslSocketFactory);
////        final TimeoutMonitor<HttpURLConnection> connectionTimeoutMonitor = httpURLConnectionTimeoutMonitor;
////        if (connectionTimeoutMonitor != null) {
////            connectionTimeoutMonitor.register(connection);
////        }
////        try {
////            HttpURLConnectionHelper.addHttpRequestHeader(connection, httpHeader);
////            HttpURLConnectionHelper.send(connection, rqMessage, requestGzip, charset);
////
////            final int responseCode = connection.getResponseCode();
////            this.responseCode.set(responseCode);
////
////            if (responseCode == HttpStatusCodes.SUCCESS) {
////                return HttpURLConnectionHelper.read(connection, connection.getInputStream(), charset);
////            }
////
////            final String errMsg = HttpURLConnectionHelper.read(connection, connection.getErrorStream(), charset);
////            if (throwHttpError || StringUtils.isBlank(errMsg)) {
////                throw new HttpErrorException(responseCode, connection.getResponseMessage());
////            }
////            return errMsg;
////        } catch (IOException e) {
////            throw new WebServiceAccessException(e);
////        } finally {
////            if (connectionTimeoutMonitor != null) {
////                connectionTimeoutMonitor.release(connection);
////            }
////            connection.disconnect();
////        }
////    }
//
//    public int getResponseCode() {
//        return responseCode.get() == null ? -1 : responseCode.get();
//    }
//
//    protected abstract String getRequestMethod();
}