package com.rusher.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;

public class IOUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(IOUtils.class);
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

    public static void closeWriter(Writer writer) {
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {
                LOGGER.error("close Writer", e);
            }
        }
    }

    public static void closeInputStream(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
                LOGGER.error("close InputStream", e);
            }
        }
    }

    public static void closeOutputStream(OutputStream out) {
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
                LOGGER.error("close OutputStream", e);
            }
        }
    }

    public static byte[] readBytes(InputStream input, int size) throws IOException {
        final byte[] bytes = new byte[size];
        int n, total = 0;

        for (; ; ) {
            n = input.read(bytes, total, bytes.length - total);
            if (EOF == n) {
                break;
            }
            total = total + n;
            if (total == bytes.length) {
                break;
            }
        }

        if (total != bytes.length) {
            throw new IOException("expect size:" + bytes.length + ", actual size:" + total);
        }

        return bytes;
    }

    public static byte[] readBytes(InputStream input) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final byte[] bytes = new byte[1024 * 4];

        int n = 0;
        while (-1 != (n = input.read(bytes))) {
            output.write(bytes, 0, n);
        }

        return output.toByteArray();
    }

    public static String toString(InputStream input) throws IOException {
        return toString(input, Charset.defaultCharset(), DEFAULT_BUFFER_SIZE);
    }

    public static String toString(InputStream input, int bufferSize) throws IOException {
        return toString(input, Charset.defaultCharset(), bufferSize);
    }

    public static String toString(InputStream input, Charset encoding) throws IOException {
        return toString(input, encoding, DEFAULT_BUFFER_SIZE);
    }

    public static String toString(InputStream input, Charset encoding, int bufferSize) throws IOException {
        final char[] buffer = new char[bufferSize];
        final StringWriter sw = new StringWriter();
        copy(new InputStreamReader(input, toCharset(encoding)), sw, buffer);
        return sw.toString();
    }

    public static void copy(InputStream input, OutputStream output) throws IOException {
        final byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        copy(input, output, buffer);
    }

    private static void copy(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        for (int n = input.read(buffer); EOF != n; n = input.read(buffer)) {
            output.write(buffer, 0, n);
        }
    }

    private static void copy(Reader input, Writer output) throws IOException {
        final char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        copy(input, output, buffer);
    }

    private static void copy(Reader input, Writer output, char[] buffer) throws IOException {
        for (int n = input.read(buffer); EOF != n; n = input.read(buffer)) {
            output.write(buffer, 0, n);
        }
    }

    private static Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }
}
