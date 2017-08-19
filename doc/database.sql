
create TABLE api_auth(
  id                int NOT NULL AUTO_INCREMENT,
  platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
  apikey            VARCHAR (250) NOT  NULL ,
  secretkey         VARCHAR (250) NOT NULL ,
  tpaextension      VARCHAR (300) DEFAULT NULL ,
  last_modify_time  TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;


create TABLE assert (
  id                INT              NOT NULL AUTO_INCREMENT,
  user              VARCHAR (100) NOT NULL ,
  platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
  market            VARCHAR(100)     NOT NULL comment 'The market of the platform',  #all, eth_cny, ltc_cny, btc_cny
  currency          VARCHAR(20)      NOT NULL, # BIT, CNY, USD etc.
  free_balance      DOUBLE PRECISION NOT NULL,
  lockedBalance     DOUBLE PRECISION NOT NULL,
  last_modify_time  TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;


create TABLE ticker (
  id                INT              NOT NULL AUTO_INCREMENT,
  platform          VARCHAR (100) NOT NULL ,   # OKCOIN, YUNBI, BITTRADE, KRAKEN, HUOBI
  market            VARCHAR(100)     NOT NULL comment 'The market of the platform',  #all, eth_cny, ltc_cny, btc_cny
  buy             DOUBLE PRECISION NOT NULL,  # 买一价 当前买入价
	high             DOUBLE PRECISION NOT NULL,  # 最高价
	last             DOUBLE PRECISION NOT NULL,  # 最新成交价
	low             DOUBLE PRECISION NOT NULL,  # 最低价
	sell             DOUBLE PRECISION NOT NULL,  # 卖一价 当前卖出价
	vol             DOUBLE PRECISION NOT NULL,  # 成交量(最近的24小时)
	server_time     TIMESTAMP NOT NULL, #返回数据时服务器时间毫秒值
  last_modify_time  TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

create TABLE trade(
  id                INT              NOT NULL AUTO_INCREMENT,

)

create TABLE setting(
  id                INT              NOT NULL AUTO_INCREMENT,
  fetch_perior     INT NOT NULL DEFAULT 10 , # 抓取数据的间隔时间 second
  last_modify_time  TIMESTAMP NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;
