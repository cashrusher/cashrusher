package com.rusher.interfaces.ws.service.db;

import com.rusher.Platform;
import com.rusher.interfaces.model.db.APIAuth;
import com.rusher.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 02/09/2017.
 */
@Service
public class KeyService extends CommonService<APIAuth> {

    public void UpdateAPIAuth(Platform platform, String secretKey, String accessKey) {
        APIAuth apiAuth = new APIAuth();
        apiAuth.setApikey(accessKey);
        apiAuth.setPlatform(platform);
        apiAuth.setSecretkey(secretKey);
        save(apiAuth);
    }
}
