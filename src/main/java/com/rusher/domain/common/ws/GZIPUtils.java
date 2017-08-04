package com.rusher.domain.common.ws;


import com.rusher.domain.common.Charsets;
import com.rusher.domain.common.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public abstract class GZIPUtils {
    private static final String[] BYTE = {"", "0", "00", "000", "0000", "00000", "000000", "0000000"};

    public static String unzipToStringByUTF8(byte[] zipData) {
        return unzipToString(zipData, Charsets.UTF8);
    }

    public static String unzipToString(byte[] zipData, Charset charset) {
        return charset == null ? new String(unzip(zipData)) : new String(unzip(zipData), charset);
    }

    public static byte[] zipStringByUTF8(String data) {
        return zipString(data, Charsets.UTF8);
    }

    public static byte[] zipString(String data, Charset charset) {
        return zip(charset == null ? data.getBytes() : data.getBytes(charset));
    }

    public static byte[] zip(byte[] data) {
        try {
            final ByteArrayOutputStream out = new ByteArrayOutputStream();
            final GZIPOutputStream gzipOut = new GZIPOutputStream(out);
            gzipOut.write(data);
            gzipOut.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("zip data", e);
        }
    }

    public static byte[] unzip(byte[] zipData) {
        final byte[] iz = new byte[4];
        iz[0] = zipData[zipData.length - 1];
        iz[1] = zipData[zipData.length - 2];
        iz[2] = zipData[zipData.length - 3];
        iz[3] = zipData[zipData.length - 4];
        final StringBuilder sb = new StringBuilder(8 * 4);
        for (byte z : iz) {
            final String v = Integer.toBinaryString(z >= 0 ? z : 256 + z);
            sb.append(BYTE[BYTE.length - v.length()]).append(v);
        }

        try {
            return IOUtils.readBytes(new GZIPInputStream(new ByteArrayInputStream(zipData)), Integer.parseInt(sb.toString(), 2));
        } catch (IOException e) {
            throw new RuntimeException("unzip data", e);
        }
    }
}