package com.rusher.kraken.utils;

import com.rusher.okcoin.service.HttpUtilManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.rusher.kraken.utils.Signature.createSignature;

/**
 * Created by liam on 09/09/2017.
 */
public class HttpUtils {

    public static String queryPrivate(String urlPath, Map<String, String> values) throws Exception {
        String urlPrex = "https://api.kraken.com";
        String secret = "WP90951w5I9uFCLabh8x0SqaKaqeTCe+orIez89Io/68R8i9Xh5lnQeSOsXtlTpf4KJ+ryf8kRMFHyRzuBpfSg==";
        byte[] secretDecode = Base64.decodeBase64(secret);
        values.put("nonce", String.valueOf(System.currentTimeMillis() * 1000000));
        String signature = createSignature(urlPath, values, secretDecode);

        System.out.println(signature);
        String content = post(urlPrex, urlPath, values, signature);
        System.out.println(content);
        return content;
    }

    public static String post(String urlPrex, String urlPath, Map<String, String> params, String signature) {
        // 构造参数签名
        Map<String, String> header = new HashMap<String, String>();
        header.put("API-Key", "RNL8qrMdKy+wRwCCR7cm5xHN09Bsew3snZIN3aW3rlnLPvtHTkCKvS+u");
        header.put("API-Sign", signature);

        // 发送post请求
        HttpUtilManager httpUtil = HttpUtilManager.getInstance();
        try {
            String result = httpUtil.requestHttpPost(urlPrex, urlPath, params, header);
            System.out.println(result);
            return result;
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
