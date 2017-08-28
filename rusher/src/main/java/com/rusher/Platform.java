package com.rusher;

/**
 * Created by liam on 19/08/2017.
 */
public enum Platform {
    OKCOIN("okcoin"), BTCTRADE("bictrade"), KRAKEN("kraken"), YUNBI("yunbi"), HUOBI("huobi");
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
