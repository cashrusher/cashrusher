package com.rusher.domain.common.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Author: Liam
 * Date: 2017/8/7
 */
public class Base64Utils {
  private final static Base64 base64 = new Base64();

  public static String toBase64(byte[] a) {
    return base64.encodeToString(a);
  }

  public static byte[] fromBase64(String s) {
    try {
      return base64.decode(s);
    } catch (Exception e) {
      throw new IllegalArgumentException("decode from Base64 error", e);
    }
  }
}
