package com.rusher.yunbi.service;

import com.rusher.yunbi.dto.Account;
import com.rusher.yunbi.dto.AuthorizationKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 13/08/2017.
 */

@Service
public class AccountService {

    @Autowired
    private YunBiAPIService yunbiAPI;

    private final String accessKey = "WNfHT5nDEtcJ9rfEJRxWQBk5bPJF55VM9AvIkgDt";
    private final String secretKey = "v6OZDWIxj1NDYRS5HPESyp1FJl640j97IUlBXXSt";

    private AuthorizationKey getAuthorizationKey() {
        AuthorizationKey authorizationKey = new AuthorizationKey();
        authorizationKey.setId(1L);
        authorizationKey.setAccessKey(accessKey); //
        authorizationKey.setSecretKey(secretKey); //
        return authorizationKey;
    }

    public Account getAccount() {
        Account account = yunbiAPI.getAccount(getAuthorizationKey());
        return account;
    }

    public YunBiAPIService getYunbiAPI() {
        return yunbiAPI;
    }

    public void setYunbiAPI(YunBiAPIService yunbiAPI) {
        this.yunbiAPI = yunbiAPI;
    }
}
