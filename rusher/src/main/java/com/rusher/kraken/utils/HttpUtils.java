package com.rusher.kraken.utils;

import com.rusher.utils.HttpUtilManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.rusher.kraken.service.KrakenService.APIURL;
import static com.rusher.kraken.utils.Signature.createSignature;

/**
 * Created by liam on 09/09/2017.
 */
public class HttpUtils {

    public static String queryPrivate(String key, String secret, String urlPath, Map<String, String> values) throws Exception {
        byte[] secretDecode = Base64.decodeBase64(secret);
        values.put("nonce", String.valueOf(System.currentTimeMillis() * 1000000));
        String signature = createSignature(urlPath, values, secretDecode);
        String content = post(APIURL + urlPath, values, key, signature);
        return content;
    }

    public static String post(String url, Map<String, String> params, String key, String signature) {
        // 构造参数签名
        Map<String, String> header = new HashMap<String, String>();
        header.put("API-Key", key);
        header.put("API-Sign", signature);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        try {
            return httpUtil.requestHttpPost(url, params, header);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
