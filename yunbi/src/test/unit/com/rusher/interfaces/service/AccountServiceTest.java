package com.rusher.interfaces.service;

import com.rusher.domain.protocol.Account;
import com.rusher.interfaces.YunBiAPIService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liam on 13/08/2017.
 */
public class AccountServiceTest {

    private YunBiAPIService apiService = new YunBiAPIService();

    @Test
    public void getAccountAssert() throws Exception {
        AccountService accountService = new AccountService();
        accountService.setYunbiAPI(apiService);
        Account account = accountService.getAccount();
        Assert.assertNotNull(account);
    }

}