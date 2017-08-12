package com.rusher.common.ws;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class WebServiceSSLSocketAlwaysTrustFactory extends SSLSocketFactory {
    private final SSLSocketFactory sslSocketFactory;


    public WebServiceSSLSocketAlwaysTrustFactory() throws Exception {
        this("SSL");
    }

    public WebServiceSSLSocketAlwaysTrustFactory(String protocol) throws Exception {
        final SSLContext sslContext = SSLContext.getInstance(protocol);
        sslContext.init(null, new TrustManager[]{createX509TrustManager()}, new SecureRandom());
        sslSocketFactory = sslContext.getSocketFactory();
    }

    public String[] getDefaultCipherSuites() {
        return sslSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return sslSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket socket, String s, int i, boolean b) throws IOException {
        return sslSocketFactory.createSocket(socket, s, i, b);
    }

    public Socket createSocket(String s, int i) throws IOException, UnknownHostException {
        return sslSocketFactory.createSocket(s, i);
    }

    public Socket createSocket(String s, int i, InetAddress inetAddress, int i1) throws IOException, UnknownHostException {
        return sslSocketFactory.createSocket(s, i, inetAddress, i1);
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return sslSocketFactory.createSocket(inetAddress, i);
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress1, int i1) throws IOException {
        return sslSocketFactory.createSocket(inetAddress, i, inetAddress1, i1);
    }

    public Socket createSocket() throws IOException {
        return sslSocketFactory.createSocket();
    }

    private static X509TrustManager createX509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }
}
