package com.rusher.bitfinex.exception;

/**
 * Created by liam on 10/09/2017.
 */
public class BalanceException extends RuntimeException {
    private String description;

    public BalanceException(String msg) {
        super(msg);
        this.description = msg;
    }

    @Override
    public String toString() {
        return "{\"msg\":\"Bitfinex Get Account balance excepiton.\"," +
                "\"description\":\"" + description + "\"}";
    }
}
