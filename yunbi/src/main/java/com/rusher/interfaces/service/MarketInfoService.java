package com.rusher.interfaces.service;

import com.rusher.domain.protocol.Market;
import com.rusher.interfaces.YunBiAPIService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liam on 13/08/2017.
 */
public class MarketInfoService {

    @Autowired
    private YunBiAPIService yunbiAPI;

    public Market GetAllMarketInfo() {
        yunbiAPI.getMarket()
    }

    public YunBiAPIService getYunbiAPI() {
        return yunbiAPI;
    }

    public void setYunbiAPI(YunBiAPIService yunbiAPI) {
        this.yunbiAPI = yunbiAPI;
    }
}
