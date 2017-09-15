package com.rusher.interfaces.ws.endpoint;

import com.rusher.interfaces.dto.SettingRequest;
import com.rusher.interfaces.dto.SettingResponse;
import com.rusher.interfaces.dto.Status;
import com.rusher.interfaces.ws.service.db.APIAuthService;
import com.rusher.interfaces.ws.service.db.SystemSettingService;
import com.rusher.interfaces.ws.support.ErrorResponseSupport;
import com.rusher.utils.JsonMessageMarshaller;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("connectionSettingProcessService")
public class ConnectionSettingProcessService implements WebServiceRequestProcessService<String, Object> {
  private final Log logger = LogFactory.getLog("ERR_LOG");

  @Autowired
  private APIAuthService APIAuthService;

  @Autowired
  private SystemSettingService settingService;

  @Autowired
  private JsonMessageMarshaller marshaller;

  @Override
  public Object processPost(String request, WebServiceRequestMessage message) {
    try {
      SettingRequest settingRequest = (SettingRequest) marshaller.doUnmarshal(request, SettingRequest.class);
      APIAuthService.updateAPIAuth(settingRequest.getPlatform(), settingRequest.getSecretKey(), settingRequest.getAccessKey());
      settingService.UpdateSystemSetting(settingRequest.getFetchRate());
      return new SettingResponse(Status.Success);
    } catch (Exception e) {
      logger.error(ExceptionUtils.getRootCauseMessage(e));
      e.printStackTrace();
      return ErrorResponseSupport.create("System", "Can not set the value due to " + e.getMessage());
    }
  }

  @Override
  public Object processGet() {
    return null;
  }
}
