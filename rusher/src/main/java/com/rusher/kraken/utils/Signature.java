package com.rusher.kraken.utils;

import com.google.common.primitives.Bytes;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Map;

/**
 * Created by liam on 09/09/2017.
 */
public class Signature {

    public static String createSignature(String urlPath, Map<String, String> values, byte[] secrets) throws Exception {
        byte[] shaSum = getSha256(Bytes.concat(values.get("nonce").getBytes(), decodeMap(values).getBytes()));
        return getHMacSha512(Bytes.concat(urlPath.getBytes(), shaSum), secrets);
    }

    private static String decodeMap(Map<String, String> values) {
        String result = "";
        for (String key : values.keySet()) {
            result = result + "&" + (key + "=" + values.get(key));
        }
        return result.substring(1);
    }

    private static String getHMacSha512(byte[] message, byte[] secret) throws Exception {
        Mac sha512_HMAC = Mac.getInstance("HmacSHA512");
        SecretKeySpec secret_key = new SecretKeySpec(secret, "HmacSHA512");
        sha512_HMAC.init(secret_key);
        return Base64.encodeBase64String(sha512_HMAC.doFinal(message));
    }

    public static String getHMacSha384(byte[] message, byte[] secret) throws Exception {
        Mac sha384_HMAC = Mac.getInstance("HmacSHA384");
        SecretKeySpec secret_key = new SecretKeySpec(secret, "HmacSHA384");
        sha384_HMAC.init(secret_key);
        return Hex.encodeHexString(sha384_HMAC.doFinal(message));
    }

    private static byte[] getSha256(byte[] nonces) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(nonces); // Change this to "UTF-16" if needed
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
