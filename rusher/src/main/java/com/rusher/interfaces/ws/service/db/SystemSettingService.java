package com.rusher.interfaces.ws.service.db;

import com.rusher.interfaces.model.db.SystemSetting;
import com.rusher.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * Created by liam on 02/09/2017.
 */
@Service
public class SystemSettingService extends CommonService<SystemSetting> {

    public void UpdateSystemSetting(int fetchRate) {
        SystemSetting systemSetting = new SystemSetting();
        systemSetting.setId(1L);
        systemSetting.setFetchRate(fetchRate);
        save(systemSetting);
    }
}
