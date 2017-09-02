package com.rusher;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by liam on 19/08/2017.
 */
public enum Platform {
    @JsonProperty("okcoin")
    OKCOIN("okcoin"),
    @JsonProperty("kraken")
    KRAKEN("kraken"),
    @JsonProperty("yunbi")
    YUNBI("yunbi"),
    @JsonProperty("huobi")
    HUOBI("huobi"),
    @JsonProperty("bitfinex")
    BITFINEX("bitfinex");
    private String platform;

    Platform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public static Platform getPlatform(String platform) {
        for (Platform c : values()) {
            if (c.getPlatform().equalsIgnoreCase(platform)) {
                return c;
            }
        }
        throw new IllegalArgumentException(platform + " is not a valid Platform");
    }
}
