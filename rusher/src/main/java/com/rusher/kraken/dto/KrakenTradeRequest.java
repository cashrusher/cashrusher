package com.rusher.kraken.dto;

/**
 * Created by liam on 09/09/2017.
 */
public class KrakenTradeRequest {
    /**
     * asset pair
     * */
    private String pair;

    /**
    type of order (buy/sell)
     */
    private Type type;

    /**
     order type:
     market
     limit (price = limit price)
     stop-loss (price = stop loss price)
     take-profit (price = take profit price)
     stop-loss-profit (price = stop loss price, price2 = take profit price)
     stop-loss-profit-limit (price = stop loss price, price2 = take profit price)
     stop-loss-limit (price = stop loss trigger price, price2 = triggered limit price)
     take-profit-limit (price = take profit trigger price, price2 = triggered limit price)
     trailing-stop (price = trailing stop offset)
     trailing-stop-limit (price = trailing stop offset, price2 = triggered limit offset)
     stop-loss-and-limit (price = stop loss price, price2 = limit price)
     settle-position
     * */
    private OrderType orderType;

    /**
     * price (optional.  dependent upon ordertype)
     * */
    private Double price;

    /**
     * secondary price (optional.  dependent upon ordertype)
     * */
    private Double price2;

    /**
     * order volume in lots
     * */
    private Double volume;

    /***
    amount of leverage desired (optional.  default = none)
     */

    private Double leverage;

    /**
     comma delimited list of order flags (optional):
     viqc = volume in quote currency (not available for leveraged orders)
     fcib = prefer fee in base currency
     fciq = prefer fee in quote currency
     nompp = no market price protection
     post = post only order (available when ordertype = limit)
     * */
    private OFlags oFlags;

    /**
     scheduled start time (optional):
     0 = now (default)
     +<n> = schedule start time <n> seconds from now
     <n> = unix timestamp of start time
     * */
    private Integer starttm;
    /**
     expiration time (optional):
     0 = no expiration (default)
     +<n> = expire <n> seconds from now
     <n> = unix timestamp of expiration time
     * */
    private Integer expiretm;

    /**
     * user reference id.  32-bit signed number.  (optional)
     * */
    private String userRef;

    /**
     *validate inputs only.  do not submit order (optional)
     * */
    private String validate;


    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice2() {
        return price2;
    }

    public void setPrice2(Double price2) {
        this.price2 = price2;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getLeverage() {
        return leverage;
    }

    public void setLeverage(Double leverage) {
        this.leverage = leverage;
    }

    public OFlags getoFlags() {
        return oFlags;
    }

    public void setoFlags(OFlags oFlags) {
        this.oFlags = oFlags;
    }

    public Integer getStarttm() {
        return starttm;
    }

    public void setStarttm(Integer starttm) {
        this.starttm = starttm;
    }

    public Integer getExpiretm() {
        return expiretm;
    }

    public void setExpiretm(Integer expiretm) {
        this.expiretm = expiretm;
    }

    public String getUserRef() {
        return userRef;
    }

    public void setUserRef(String userRef) {
        this.userRef = userRef;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }
}
