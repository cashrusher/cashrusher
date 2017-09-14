package com.rusher.interfaces.ws.service.db;

import com.rusher.interfaces.dto.ExchangeKey;
import com.rusher.interfaces.dto.ThresholdSetting;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by liam on 02/09/2017.
 */
@Service
public class SystemSettingService extends CommonService<SystemSetting> {

  @Transactional
  public void UpdateSystemSetting(int fetchRate) {
    System.out.println(fetchRate);
    SystemSetting systemSetting = load(1L);
    if (systemSetting == null) {
      systemSetting = new SystemSetting();
    }
    systemSetting.setFetchRate(fetchRate);
    save(systemSetting);
  }

  @Transactional
  public void UpdateSystemSetting(ThresholdSetting request) {
    SystemSetting systemSetting = load(1L);
    systemSetting.setMaxBuyBitfinexSell(request.getMaxBuyBitfinexSell());
    systemSetting.setMaxBuyKrakenSell(request.getMaxBuyKrakenSell());
    systemSetting.setMaxBuyMinSell(request.getMaxBuyMinSell());
    systemSetting.setMaxSellBitFinexBuy(request.getMaxSellBitfinexBuy());
    systemSetting.setMaxSellKrakenBuy(request.getMaxSellKrakenBuy());
    save(systemSetting);
  }

  @Transactional
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

  @Transactional(readOnly = true)
  public SystemSetting getSystemSetting() {
    return load(1L);
  }
}
