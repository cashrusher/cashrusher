package com.rusher.model.db;

import com.rusher.db.PersistenceSupport;

import javax.persistence.*;

/**
 * Created by liam on 19/08/2017.
 * <p>
 * create TABLE assert (
 * id                INT              NOT NULL AUTO_INCREMENT,
 * user              VARCHAR (100) NOT NULL ,
 * platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
 * market            VARCHAR(100)     NOT NULL comment 'The market of the platform',  #all, eth_cny, ltc_cny, btc_cny
 * currency          VARCHAR(20)      NOT NULL, # BIT, CNY, USD etc.
 * free_balance      DOUBLE PRECISION NOT NULL,
 * locked_balance     DOUBLE PRECISION NOT NULL,
 * last_modify_time  TIMESTAMP NOT NULL,
 * PRIMARY KEY (id)
 * )ENGINE = InnoDB DEFAULT CHARSET = utf8;
 */


@Entity
@Table(
        name = "assert"
)
public class Assert extends PersistenceSupport {
    @Column(nullable = false)
    private String user;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Platform platform = Platform.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Market market = Market.ALL;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Curreny currency = Curreny.CNY;

    @Column(nullable = false)
    private String freeBalance;

    @Column(nullable = false)
    private String lockedBalance;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public Curreny getCurrency() {
        return currency;
    }

    public void setCurrency(Curreny currency) {
        this.currency = currency;
    }

    public String getFreeBalance() {
        return freeBalance;
    }

    public void setFreeBalance(String freeBalance) {
        this.freeBalance = freeBalance;
    }

    public String getLockedBalance() {
        return lockedBalance;
    }

    public void setLockedBalance(String lockedBalance) {
        this.lockedBalance = lockedBalance;
    }
}
