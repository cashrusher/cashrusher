package com.rusher.interfaces.model.db;

import com.rusher.db.PersistenceSupport;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by liam on 02/09/2017.
 * <p>
 * /{
 * //        "maxbuy_krakensell":1.80,
 * //        "maxsell_krakenbuy":1.73,
 * //        "maxbuy_bitfinexsell":2.12,
 * //        "maxsell_bitfinexbuy":2.13,
 * //        "maxbuy_minsell":1.98
 * //        }
 */

@Entity
@Table(
        name = "system_setting"
)
public class SystemSetting extends PersistenceSupport {

    private int fetchRate;

    private double cnyusd;

    private double maxBuyKrakenSell;

    private double maxSellKrakenBuy;

    private double maxBuyBitfinexSell;

    private double maxSellBitFinexBuy;

    private double maxBuyMinSell;


    public static SystemSetting DefaultSetting() {
        SystemSetting systemSetting = new SystemSetting();
        systemSetting.setId(1L);
        systemSetting.setFetchRate(10);
        return systemSetting;
    }

    public double getCnyusd() {
        return cnyusd;
    }

    public void setCnyusd(double cnyusd) {
        this.cnyusd = cnyusd;
    }

    public int getFetchRate() {
        return fetchRate;
    }

    public void setFetchRate(int fetchRate) {
        this.fetchRate = fetchRate;
    }

    public double getMaxBuyKrakenSell() {
        return maxBuyKrakenSell;
    }

    public void setMaxBuyKrakenSell(double maxBuyKrakenSell) {
        this.maxBuyKrakenSell = maxBuyKrakenSell;
    }

    public double getMaxSellKrakenBuy() {
        return maxSellKrakenBuy;
    }

    public void setMaxSellKrakenBuy(double maxSellKrakenBuy) {
        this.maxSellKrakenBuy = maxSellKrakenBuy;
    }

    public double getMaxBuyBitfinexSell() {
        return maxBuyBitfinexSell;
    }

    public void setMaxBuyBitfinexSell(double maxBuyBitfinexSell) {
        this.maxBuyBitfinexSell = maxBuyBitfinexSell;
    }

    public double getMaxSellBitFinexBuy() {
        return maxSellBitFinexBuy;
    }

    public void setMaxSellBitFinexBuy(double maxSellBitFinexBuy) {
        this.maxSellBitFinexBuy = maxSellBitFinexBuy;
    }

    public double getMaxBuyMinSell() {
        return maxBuyMinSell;
    }

    public void setMaxBuyMinSell(double maxBuyMinSell) {
        this.maxBuyMinSell = maxBuyMinSell;
    }
}
