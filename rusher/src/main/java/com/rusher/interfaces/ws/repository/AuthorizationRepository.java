package com.rusher.interfaces.ws.repository;

import com.rusher.Authorization;
import com.rusher.Platform;

/**
 * Author: Liam
 * Date: 2017/9/15
 */
public interface AuthorizationRepository {
  public Authorization getAuth(Platform platform);
}
