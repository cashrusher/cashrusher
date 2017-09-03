package com.rusher.interfaces.ws.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rusher.interfaces.dto.ExchangeKey;
import com.rusher.interfaces.dto.ExchangeRateSetting;
import com.rusher.interfaces.dto.SettingResponse;
import com.rusher.interfaces.dto.Status;
import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.interfaces.ws.service.db.SystemSettingService;
import com.rusher.interfaces.ws.support.ErrorResponseSupport;
import com.rusher.utils.JsonMessageMarshaller;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("exchangeRateSettingProcessService")
public class ExchangeRateSettingProcessService implements WebServiceRequestProcessService<String, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");

    @Autowired
    private SystemSettingService settingService;

    @Autowired
    private JsonMessageMarshaller marshaller;

    @Override
    @Transactional(readOnly = false)
    public Object processPost(String request, WebServiceRequestMessage message) {
        try {
            ExchangeRateSetting settingRequest = (ExchangeRateSetting) marshaller.doUnmarshal(request, ExchangeRateSetting.class);
            settingService.UpdateSystemSetting(settingRequest.getExchangeRate());
            return new SettingResponse(Status.Success);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
            return ErrorResponseSupport.create("System", "Can not set the value due to " + e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Object processGet() {
        SystemSetting systemSetting = settingService.getSystemSetting();
        ExchangeRateSetting exchangeRateSetting = new ExchangeRateSetting();
        exchangeRateSetting.setExchangeRate(createExchangeRate(systemSetting));
        return exchangeRateSetting;
    }

    private List<Map<ExchangeKey, Double>> createExchangeRate(SystemSetting systemSetting) {
        List<Map<ExchangeKey, Double>> exchangeKeyMaps = Lists.newArrayList();
        Map<ExchangeKey, Double> map = Maps.newConcurrentMap();
        map.put(ExchangeKey.CNYUSD, systemSetting.getCnyusd());
        exchangeKeyMaps.add(map);
        return exchangeKeyMaps;
    }
}
