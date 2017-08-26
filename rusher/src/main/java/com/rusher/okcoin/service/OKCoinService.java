package com.rusher.okcoin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusher.Authorization;
import com.rusher.okcoin.dto.OKCoinAssert;
import org.springframework.stereotype.Service;

import static com.rusher.okcoin.constant.OKCOIN_URL;

/**
 * Created by liam on 26/08/2017.
 */
@Service
public class OKCoinService {
    private ObjectMapper mapper = new ObjectMapper();
    public OKCoinAssert getAssert(Authorization auth) {
        IStockRestApi stockPost = new StockRestApiService(OKCOIN_URL, auth.getApiKey(), auth.getSecretKey());
        try {
            String user = stockPost.userinfo();
            System.out.println(user);
            OKCoinAssert anAssert = mapper.readValue(user, OKCoinAssert.class);
            return anAssert;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
