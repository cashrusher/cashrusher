# cashrusher

# 系统配置
## Maven的setting.xml文件的配置：

```xml
	<mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
  </mirrors>

```

## Client for YunBi

https://github.com/peatio/peatio-client-java.git

## About the database

We use mariaDB(same as MySQL).
 1. MariaDB for Mac installation: https://mariadb.com/kb/en/the-mariadb-library/installing-mariadb-on-macos-using-homebrew/
 2. MariaDB for windows installation:
  
# APIs

## 获取个人资产

    URL:  http://35.164.34.250/rusher/json/asset
    HTTP GET
  
  Response:
  
  ```json
  {
      "total":{
        "ratio":"1:6.6514",
        "cny":329.78,
        "usd":49.58
      },
      "detail":{
        "okcoin":[
          {
            "currency":"cny",
            "available":1.00878,
            "locked":0.0123,
            "cny":1.00878,
            "usd":0.15166
          },
          {
            "currency":"eth",
            "available":0.072952,
            "locked":0,
            "cny":147.36304,
            "usd":0.15166
          }
        ],
        "yunbi":[
          {
            "currency":"cny",
            "available":1.00878,
            "locked":0.0123,
            "cny":1.00878,
            "usd":0.15166
          },
          {
            "currency":"ans",
            "available":1,
            "locked":0,
            "cny":164.001,
            "usd":24.65661
          }
        ]
      }
  }
  ```
  
## 设置页面

    URL:  http://35.164.34.250/rusher/
    HTTP POST
  
  Response
  ```json
  {
      
  }
  ```
  
  
## 价差套利数据展示接口

前端页面每隔30s调用一次，通过此接口来获取最新数据并刷新页面展示。

    URL:  http://35.164.34.250/rusher/json/jiachadata
    HTTP POST
  
  Response
  ```json
{
  "mainland": {
    "tickers": [
      {
        "platform": "okcoin",
        "currency": "cny",
        "buy": 22504.23,
        "sell": 22521.22
      },
      {
        "platform": "yunbi",
        "currency": "cny",
        "buy": 22201.32,
        "sell": 22543.22
      }
    ],
    "calculate": {
      "maxsell": 22543.22,
      "minbuy": 22201.32,
      "maxsellminbuyratio":1.023
    }
  },
  "abroad": {
    "tickers": [
      {
        "platform": "kraken",
        "currency": "usd",
        "buy": 156.20,
        "sell": 169.44
      },
      {
        "platform": "kraken",
        "currency": "cny",
        "buy": 22201.32,
        "sell": 22543.22
      }
    ],
    "calculate": {
      "kraken": {
        "sellratio": 0.21,
        "buyratio": 0.12
      },
      "bitfinex": {
        "sellratio": 0.13,
        "buyratio": 0.24
      }
    }
  },
  "cnyusdratio":6.45
  
  ```
}