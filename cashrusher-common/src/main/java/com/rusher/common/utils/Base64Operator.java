package com.rusher.common.utils;

public interface Base64Operator {
    String toBase64(byte[] a);

    byte[] fromBase64(String s);
}
