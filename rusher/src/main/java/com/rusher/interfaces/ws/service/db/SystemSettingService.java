package com.rusher.interfaces.ws.service.db;

import com.rusher.interfaces.dto.ExchangeKey;
import com.rusher.interfaces.dto.ThresholdSettingRequest;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 02/09/2017.
 */
@Service
public class SystemSettingService extends CommonService<SystemSetting> {

    public void UpdateSystemSetting(int fetchRate) {
        SystemSetting systemSetting = load(1L);
        systemSetting.setFetchRate(fetchRate);
        save(systemSetting);
    }

    public void UpdateSystemSetting(ThresholdSettingRequest request) {
        SystemSetting systemSetting = load(1L);
        systemSetting.setMaxBuyBitfinexSell(request.getMaxBuyBitfinexSell());
        systemSetting.setMaxBuyKrakenSell(request.getMaxBuyKrakenSell());
        systemSetting.setMaxBuyMinSell(request.getMaxBuyMinSell());
        systemSetting.setMaxSellBitFinexBuy(request.getMaxSellBitfinexBuy());
        systemSetting.setMaxSellKrakenBuy(request.getMaxSellKrakenBuy());
        save(systemSetting);
    }

    public void UpdateSystemSetting(List<Map<ExchangeKey, Double>> exchangeSetting) {
        SystemSetting systemSetting = load(1L);
        for (Map<ExchangeKey, Double> keyDoubleMap : exchangeSetting) {
            Double cnyusd = keyDoubleMap.get(ExchangeKey.CNYUSD);
            if (cnyusd != null) {
                systemSetting.setCnyusd(cnyusd.doubleValue());
            }
        }
        save(systemSetting);
    }

    public SystemSetting getSystemSetting() {
        return load(1L);
    }
}
