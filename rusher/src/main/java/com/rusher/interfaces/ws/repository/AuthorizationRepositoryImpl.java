package com.rusher.interfaces.ws.repository;

import com.rusher.Authorization;
import com.rusher.Platform;
import com.rusher.interfaces.model.db.APIAuth;
import com.rusher.interfaces.ws.exception.AuthorizationException;
import com.rusher.interfaces.ws.service.db.APIAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rusher.interfaces.model.Constant.SYSTEM;

/**
 * Author: Liam
 * Date: 2017/9/15
 */
@Service("authorizationRepository")
public class AuthorizationRepositoryImpl implements AuthorizationRepository {
  @Autowired
  private APIAuthService apiAuthService;

  @Override
  public Authorization getAuth(Platform platform) {
    APIAuth apiAuth = apiAuthService.getAPIAuth(platform);
    if (apiAuth == null) {
      throw new AuthorizationException(SYSTEM, "Can not find API Authorization Key info for:" + platform + ", you can input it in the Trade-Config-Setting.");
    }
    return new Authorization(apiAuth.getApikey(), apiAuth.getSecretkey());
  }

}
