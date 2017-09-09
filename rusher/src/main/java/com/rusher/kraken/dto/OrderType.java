package com.rusher.kraken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * market
 * limit (price = limit price)
 * stop-loss (price = stop loss price)
 * take-profit (price = take profit price)
 * stop-loss-profit (price = stop loss price, price2 = take profit price)
 * stop-loss-profit-limit (price = stop loss price, price2 = take profit price)
 * stop-loss-limit (price = stop loss trigger price, price2 = triggered limit price)
 * take-profit-limit (price = take profit trigger price, price2 = triggered limit price)
 * trailing-stop (price = trailing stop offset)
 * trailing-stop-limit (price = trailing stop offset, price2 = triggered limit offset)
 * stop-loss-and-limit (price = stop loss price, price2 = limit price)
 * settle-position
 */

public enum OrderType {
    @JsonProperty("market")
    MARKET("market"),
    LIMIT("limit"), //(price = limit price)
    STOP_LOSS("stop_loss"),// (price = stop loss price)
    TAKE_PROFIT("take-profit"),// (price = take profit price)
    STOP_LOSS_PROFIT("stop-loss-profit"),// (price = stop loss price, price2 = take profit price)
    STOP_LOSS_PROFIX_LIMIT("stop-loss-profit-limit"),// (price = stop loss price, price2 = take profit price)
    STOP_LOSS_LIMIT("stop-loss-limit"),// (price = stop loss trigger price, price2 = triggered limit price)
    TAKE_PROFIT_LIMIT("take-profit-limit"),// (price = take profit trigger price, price2 = triggered limit price)
    TRAI_LING_STOP("trailing-stop"),// (price = trailing stop offset)
    TRAILING_STOP_LIMIT("trailing-stop-limit"),// (price = trailing stop offset, price2 = triggered limit offset)
    STOP_LOSS_AND_LIMIT("stop-loss-and-limit"),// (price = stop loss price, price2 = limit price)
    SETTLE_POSITION("settle-position");

    private String orderType;

    OrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getOrderType() {
        return orderType.toLowerCase();
    }

    public static OrderType getOrderType(String orderType) {
        for (OrderType c : values()) {
            if (c.getOrderType().equalsIgnoreCase(orderType)) {
                return c;
            }
        }
        throw new IllegalArgumentException(orderType + " is not a valid orderType");
    }
}

