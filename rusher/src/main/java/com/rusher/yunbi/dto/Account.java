package com.rusher.yunbi.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by liam on 13/08/2017.
 */

/**
 * 以下为 2017-08-13 测试所得数据
 * https://yunbi.com//api/v2/members/me.json?access_key=WNfHT5nDEtcJ9rfEJRxWQBk5bPJF55VM9AvIkgDt&tonce=1502608329680&signature=44219b81c200e0b816f9f6a44a725498d2ad62b5b23b191c30cc46d1e4948269
 * {
 * "sn": "PEANMVNSFJPTIO",
 * "name": "付万进",
 * "email": "woshifuwanjin@126.com",
 * "activated": true,
 * "memo": "313590816",
 * "accounts": [
 * {
 * "currency": "cny",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "btc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "ltc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "doge",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "bts",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "bitcny",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "bitusd",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "bitbtc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "note",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "pls",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "nxt",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "eth",
 * "balance": "2.91",
 * "locked": "0.0"
 * },
 * {
 * "currency": "sc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "dgd",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "dcs",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "dao",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "etc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "amp",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "1st",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "rep",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "ans",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "zec",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "zmc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "gnt",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "gxs",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "qtum",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "eos",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "snt",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "bcc",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "omg",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "lun",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "pay",
 * "balance": "0.0",
 * "locked": "0.0"
 * },
 * {
 * "currency": "yun",
 * "balance": "0.0",
 * "locked": "0.0"
 * }
 * ]
 * }
 */

public class Account {
    // 用户的唯一编号
    @JSONField(name = "sn")
    private String uniqueID;
    // 用户名字
    @JSONField(name = "name")
    private String name;
    // 用户邮件地址
    @JSONField(name = "email")
    private String email;
    // 用户是否已激活
    @JSONField(name = "activated")
    private Boolean isActivated;
    // 用户的注册ID
    @JSONField(name = "memo")
    private String registID;
    //用户的所有资产
    @JSONField(name = "accounts")
    private List<AccountAsset> accountAssets;

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getRegistID() {
        return registID;
    }

    public void setRegistID(String registID) {
        this.registID = registID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public List<AccountAsset> getAccountAssets() {
        return accountAssets;
    }

    public void setAccountAssets(List<AccountAsset> accountAssets) {
        this.accountAssets = accountAssets;
    }
}
