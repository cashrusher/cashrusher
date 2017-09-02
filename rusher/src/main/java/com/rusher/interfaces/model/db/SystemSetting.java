package com.rusher.interfaces.model.db;

import com.rusher.db.PersistenceSupport;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liam on 02/09/2017.
 */

@Entity
@Table(
        name = "system_setting"
)
public class SystemSetting extends PersistenceSupport {

    private int fetchRate;


    public static SystemSetting DefaultSetting() {
        SystemSetting systemSetting = new SystemSetting();
        systemSetting.setId(1L);
        systemSetting.setFetchRate(10);
        return systemSetting;
    }

    public int getFetchRate() {
        return fetchRate;
    }

    public void setFetchRate(int fetchRate) {
        this.fetchRate = fetchRate;
    }
}
