package com.rusher.yunbi.service;

import com.rusher.Authorization;
import com.rusher.Currency;
import com.rusher.utils.JsonMessageMarshaller;
import com.rusher.yunbi.dto.*;
import com.rusher.yunbi.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 13/08/2017.
 */

@Service
public class YunBiService {

    @Autowired
    private YunBiAPI yunbiAPI;

    @Autowired
    JsonMessageMarshaller marshaller;

    private AuthorizationKey getAuthorizationKey(String accessKey, String secretKey) {
        AuthorizationKey authorizationKey = new AuthorizationKey();
        authorizationKey.setId(1L);
        authorizationKey.setAccessKey(accessKey);
        authorizationKey.setSecretKey(secretKey);
        return authorizationKey;
    }

    public Account getAccount(Authorization authorization) {
        return yunbiAPI.getAccount(getAuthorizationKey(authorization.getApiKey(), authorization.getSecretKey()));
    }

    public YunBiTicker getTicker(Currency currency) {
        try {
            String ticker = yunbiAPI.tickerStr(Constant.CurrencyAndSymbolMap.get(currency));
            System.out.println(ticker);
            return (YunBiTicker) marshaller.doUnmarshal(ticker, YunBiTicker.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public YunBiAPI getYunbiAPI() {
        return yunbiAPI;
    }

    public void setYunbiAPI(YunBiAPI yunbiAPI) {
        this.yunbiAPI = yunbiAPI;
    }


    public JsonMessageMarshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(JsonMessageMarshaller marshaller) {
        this.marshaller = marshaller;
    }
}
