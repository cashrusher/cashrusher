package com.rusher.interfaces.ws.service;

import com.rusher.interfaces.dto.SettingResponse;
import com.rusher.interfaces.dto.Status;
import com.rusher.interfaces.dto.ThresholdSetting;
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

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("thresholdSettingProcessService")
public class ThresholdSettingProcessService implements WebServiceRequestProcessService<String, Object> {
    private final Log logger = LogFactory.getLog("ERR_LOG");

    @Autowired
    private SystemSettingService settingService;

    @Autowired
    private JsonMessageMarshaller marshaller;

    @Override
    @Transactional(readOnly = false)
    public Object processPost(String request, WebServiceRequestMessage message) {
        try {
            ThresholdSetting settingRequest = (ThresholdSetting) marshaller.doUnmarshal(request, ThresholdSetting.class);
            settingService.UpdateSystemSetting(settingRequest);
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
        ThresholdSetting thresholdSetting=new ThresholdSetting();
        thresholdSetting.setMaxSellKrakenBuy(systemSetting.getMaxSellKrakenBuy());
        thresholdSetting.setMaxBuyMinSell(systemSetting.getMaxBuyMinSell());
        thresholdSetting.setMaxBuyKrakenSell(systemSetting.getMaxBuyKrakenSell());
        thresholdSetting.setMaxBuyBitfinexSell(systemSetting.getMaxBuyBitfinexSell());
        thresholdSetting.setMaxSellBitfinexBuy(systemSetting.getMaxSellBitFinexBuy());
        return thresholdSetting;
    }
}
