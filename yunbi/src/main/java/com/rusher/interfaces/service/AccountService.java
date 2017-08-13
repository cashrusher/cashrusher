package com.rusher.interfaces.service;

import com.rusher.domain.protocol.Account;
import com.rusher.domain.protocol.AppAccount;
import com.rusher.interfaces.YunBiAPIService;
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

    public AppAccount createAccount() {
        AppAccount appAccount = new AppAccount();
        appAccount.setId(1L);
        appAccount.setAccessKey(accessKey); //
        appAccount.setSecretKey(secretKey); //
        return appAccount;
    }

    public AppAccount getAccountAssert() {
        Account account = yunbiAPI.getAccount();

        return null;
    }

}
