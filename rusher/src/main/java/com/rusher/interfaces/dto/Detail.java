package com.rusher.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by liam on 26/08/2017.
 */
public class Detail {
    @JsonProperty("okcoin")
    private List<PlatformAsset> okcoin;

    @JsonProperty("yunbi")
    private List<PlatformAsset>  yunbi;

    @JsonProperty("huobi")
    private List<PlatformAsset>  huobi;

    @JsonProperty("craken")
    private List<PlatformAsset>  craken;

    public List<PlatformAsset> getOkcoin() {
        return okcoin;
    }

    public void setOkcoin(List<PlatformAsset> okcoin) {
        this.okcoin = okcoin;
    }

    public List<PlatformAsset> getYunbi() {
        return yunbi;
    }

    public void setYunbi(List<PlatformAsset> yunbi) {
        this.yunbi = yunbi;
    }

    public List<PlatformAsset> getHuobi() {
        return huobi;
    }

    public void setHuobi(List<PlatformAsset> huobi) {
        this.huobi = huobi;
    }

    public List<PlatformAsset> getCraken() {
        return craken;
    }

    public void setCraken(List<PlatformAsset> craken) {
        this.craken = craken;
    }
}
