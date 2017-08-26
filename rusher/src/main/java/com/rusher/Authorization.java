package com.rusher;

/**
 * Created by liam on 26/08/2017.
 */
public class Authorization {
    private String apiKey;
    private String secretKey;
    //OKCoin申请的apiKey
    //14d0881c-68b8-4de7-8ef5-b2140ba2780c
    //0440198DB0B9D02BBF0F240AB220208A
    public Authorization(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
