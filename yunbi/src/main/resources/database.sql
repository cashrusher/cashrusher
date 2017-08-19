##

CREATE DATABASE cashrusher;

CREATE TABLE assert (
  id          INT              NOT NULL AUTO_INCREMENT,
  platform    VARCHAR(200)     NOT NULL comment 'The platform like Okcoin, yunbi, huobi etc',
  userID      INT              NOT NULL comment 'The account user id',
  isActivated BOOL DEFAULT TRUE comment '',
  market      VARCHAR(100)     NOT NULL comment 'The market of the platform',
  amount      DOUBLE PRECISION NOT NULL comment '',
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;


CREATE TABLE account (
  id            INT AUTO_INCREMENT NOT NULL,
  platform      VARCHAR(200)     NOT NULL comment 'The platform like Okcoin, yunbi, huobi etc',
  identity_num  VARCHAR(255)     NOT NULL,
  name          VARCHAR(100)     NOT NULL,
  currency      VARCHAR(20)      NOT NULL,
  free_balance  DOUBLE PRECISION NOT NULL,
  lockedBalance DOUBLE PRECISION NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;


CREATE TABLE market (
  id        INT              NOT NULL comment 'auto increase id',
  timestamp BIGINT           NOT NULL comment 'millisecond time',
  market    VARCHAR(10)      NOT NULL comment 'Which market',
  platform  VARCHAR(10)      NOT NULL comment 'YUNBI,OKCOIN,HUOBI,KRAKEN,BITCOIN,JUBI',
  buy       DOUBLE PRECISION NOT NULL comment 'Every time schedual buy price',
  sell      DOUBLE PRECISION comment 'Every time schedual sell price'

)