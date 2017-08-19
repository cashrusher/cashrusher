package com.rusher.model.db;

import com.rusher.db.PersistenceSupport;

import javax.persistence.*;

/**
 * Created by liam on 19/08/2017.
 * <p>
 * <p>
 * create TABLE ticker (
 * id                INT              NOT NULL AUTO_INCREMENT,
 * platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
 * market            VARCHAR(100)     NOT NULL comment 'The market of the platform',  #all, eth_cny, ltc_cny, btc_cny
 * buy             DOUBLE PRECISION NOT NULL,  # 买一价 当前买入价
 * high             DOUBLE PRECISION NOT NULL,  # 最高价
 * last             DOUBLE PRECISION NOT NULL,  # 最新成交价
 * low             DOUBLE PRECISION NOT NULL,  # 最低价
 * sell             DOUBLE PRECISION NOT NULL,  # 卖一价 当前卖出价
 * vol             DOUBLE PRECISION NOT NULL,  # 成交量(最近的24小时)
 * server_time     TIMESTAMP NOT NULL, #返回数据时服务器时间毫秒值
 * last_modify_time  TIMESTAMP NOT NULL,
 * PRIMARY KEY (id)
 * )ENGINE = InnoDB DEFAULT CHARSET = utf8;
 */

@Entity
@Table(
        name = "ticker"
)
public class Ticker extends PersistenceSupport {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Platform platform = Platform.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Market market = Market.ALL;

    @Column(nullable = false)
    private float buy;

    @Column(nullable = false)
    private float high;

    @Column(nullable = false)
    private float last;

    @Column(nullable = false)
    private float low;

    @Column(nullable = false)
    private float sell;

    @Column(nullable = false)
    private float vol;

    @Column(nullable = false)
    private int server_time;


    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public float getBuy() {
        return buy;
    }

    public void setBuy(float buy) {
        this.buy = buy;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLast() {
        return last;
    }

    public void setLast(float last) {
        this.last = last;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getVol() {
        return vol;
    }

    public void setVol(float vol) {
        this.vol = vol;
    }

    public int getServer_time() {
        return server_time;
    }

    public void setServer_time(int server_time) {
        this.server_time = server_time;
    }
}
