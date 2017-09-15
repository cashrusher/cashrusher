package com.rusher.interfaces.ws.endpoint;

import com.rusher.interfaces.dto.AssetResponse;
import com.rusher.interfaces.ws.service.PersonalAssetService;
import com.rusher.interfaces.ws.support.ErrorResponseSupport;
import com.rusher.ws.WebServiceRequestMessage;
import com.rusher.ws.WebServiceRequestProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static com.rusher.interfaces.model.Constant.SYSTEM;

/**
 * Author: Liam
 * Date: 2017/8/23
 */
@Service("assetProcessService")
public class AssetProcessService implements WebServiceRequestProcessService<AssetResponse, Object> {
  private final Log logger = LogFactory.getLog("ERR_LOG");

  @Autowired
  private PersonalAssetService service;

  @Override
  public Object processPost(AssetResponse response, WebServiceRequestMessage message) {
    return ErrorResponseSupport.create(SYSTEM, "HTTP GET Required.");
  }

  //   http://localhost:8088/rusher/json/asset
  @Override
  public Object processGet() {
    try {
      return service.getPersonalAsset();
    } catch (Exception e) {
      return ErrorResponseSupport.create(SYSTEM, ErrorResponseSupport.getStackTrace(e));
    }
  }
}
