package com.rusher.yunbi.service;

import com.rusher.Authorization;
import com.rusher.yunbi.dto.Account;
import com.rusher.yunbi.dto.AuthorizationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 13/08/2017.
 */

@Service
public class YunBiService {

    @Autowired
    private YunBiAPI yunbiAPI;


    private AuthorizationKey getAuthorizationKey(String accessKey, String secretKey) {
        AuthorizationKey authorizationKey = new AuthorizationKey();
        authorizationKey.setId(1L);
        authorizationKey.setAccessKey(accessKey); //
        authorizationKey.setSecretKey(secretKey); //
        return authorizationKey;
    }

    public Account getAccount(Authorization authorization) {
        return yunbiAPI.getAccount(getAuthorizationKey(authorization.getApiKey(), authorization.getSecretKey()));
    }

    public YunBiAPI getYunbiAPI() {
        return yunbiAPI;
    }

    public void setYunbiAPI(YunBiAPI yunbiAPI) {
        this.yunbiAPI = yunbiAPI;
    }
}
