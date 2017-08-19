package com.rusher.model.db;

import com.rusher.db.PersistenceSupport;

import javax.persistence.*;

/**
 * Created by liam on 19/08/2017.
 *
 * create TABLE api_auth(
 *      id                int NOT NULL AUTO_INCREMENT,
 *      platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
 *      apikey            VARCHAR (250) NOT  NULL ,
 *      secretkey         VARCHAR (250) NOT NULL ,
 *      tpaextension      VARCHAR (300) DEFAULT NULL ,
 *      last_modify_time  TIMESTAMP NOT NULL,
 *      PRIMARY KEY (id)
 * )ENGINE = InnoDB DEFAULT CHARSET = utf8;
 *
 */

@Entity
@Table(
        name = "api_auth"
)
public class APIAuth extends PersistenceSupport {

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false)
    private String apikey;

    @Column(nullable = false)
    private String secretkey;

    @Column(nullable = false)
    private String tpaextension;

    public String getApikey() {
        return apikey;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getTpaextension() {
        return tpaextension;
    }

    public void setTpaextension(String tpaextension) {
        this.tpaextension = tpaextension;
    }
}
