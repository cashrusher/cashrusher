package com.rusher.domain.protocol;

/**
 * Created by liam on  2017-08-13
 */
public enum Currency {
    CNY("cny"), USD("usd");

    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public static Currency getByCurrency(String currency) {
        for (Currency c : values()) {
            if (c.getCurrency().equalsIgnoreCase(currency)) {
                return c;
            }
        }
        throw new IllegalArgumentException(currency + " is not a valid Currency");
    }
}
