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

## Linux Server 配置

### 添加用户
adduser test
passwd test
Changing password for user tommy.
New UNIX password:     //在这里输入新密码
Retype new UNIX password:  //再次输入新密码
passwd: all authentication tokens updated successfully.

修改 /etc/sudoers 文件，找到下面一行，在root下面添加一行，如下所示：

`## Allow root to run any commands anywhere`
root    ALL=(ALL)     ALL
tommy   ALL=(ALL)     ALL

修改完毕，现在可以用tommy帐号登录，然后用命令 su - ，即可获得root权限进行操作

## Client for YunBi

https://github.com/peatio/peatio-client-java.git

## About the database

We use mariaDB(same as MySQL).
 1. MariaDB for Mac installation: https://mariadb.com/kb/en/the-mariadb-library/installing-mariadb-on-macos-using-homebrew/
 2. MariaDB for windows installation:
  
# APIs

API 遵从RestFul风格，使用JSON数据传输格式，URL以及JSON数据的内容均为英文，且均为小写。

## 获取个人资产

    URL:  http://52.41.94.237/rusher/json/asset
    HTTP GET
    
  每个平台的小记功能由前端算出，人民币和美元保留2位小数，其他的虚拟货币保留6位
  需要注意的是，由于虚拟货币对应现实货币的价格浮动比较频繁，所以在这里展示的美元和人民币的价值是页面刷新的那一刻的值。
  
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

主要用于设置页面的设置功能

platform      :前端只能传输["okcoin","yunbi","kraken","bitbinex"]
fetchrate     :只能为整数，单位为xx秒／次，代表市场行情的后台刷新频率，即调用第三方网站的接口的频率

    URL:  http://52.41.94.237/rusher/settings/platform
    HTTP POST
  
  Request
  ```json
  {
    "accesskey":"jkdksdljsdljfye",
    "secretkey":"9829087292",
    "platform":"okcoin",
    "fetchrate": 3
  }
  ```
 
  Response
  ```json
{
  "status":"success"
}
  ```
  
## 设置页面的测试链接功能

platform      :前端只能传输["okcoin","yunbi","kraken","bitbinex"]
accesskey 以及 secretkey 不能包含空格

    URL:  http://52.41.94.237/rusher/settings/testconnection
    HTTP POST
  
  Request
  ```json
  {
     "accesskey":"jkdksdljsdljfye",
     "secretkey":"9829087292",
     "platform":"okcoin"
  }
  ```
 
  Response
  ```json
  {
     "status":"success",
     "message":"connection ok, using your okcoin account: wang"
  }
  ```
  
  Response
  ```json
  {
     "status":"failed",
     "error":{
         "code": "RemoteServiceError",
         "message": "Invalid user authorization."
      }  
   }
   
   ```
 
## 获取后端是否达到了价差自动交易的阈值

前端每隔5秒调用一次此接口，如果此接口返回true，则前端应该立刻调用一次 <价差套利数据展示接口> 来更新价差套利的数据展示

    URL:  http://52.41.94.237/rusher/threshold_trigger/jiacha
    HTTP GET
  
  Response
  ```json
{
   "trigger":true
}
```

## 价差套利数据展示接口

前端页面每隔10s调用一次，通过此接口来获取最新数据并刷新页面展示。

    URL:  http://52.41.94.237/rusher/data/jiacha
    HTTP GET
  
  Response
  ```json
{
  "mainland": {
    "tickers": {
      "okcoin":[
        {  
           "currency": "cny",
           "buy": 22504.23,
           "sell": 22521.22
         },{
           "currency": "cny",
           "buy": 22201.32,
           "sell": 22543.22
         }
        ]    
    },
    "calculate": {
      "maxsell": 22543.22,
      "minbuy": 22201.32,
      "maxsellminbuyratio":1.023
    }
  },
  "abroad": {
    "tickers": {
       "kraken":[
          {
             "currency": "usd",
             "buy": 156.20,
             "sell": 169.44
           },
           {
             "currency": "cny",
             "buy": 22201.32,
             "sell": 22543.22
           }
       ],
       "bitfinex":[
           {
             "currency": "cny",
             "buy": 20001.32,
             "sell": 19943.22
            }
       ]
     },
    "calculate": [
      {
        "platform":"kraken",
        "sellratio": 0.021,
        "buyratio": 0.012
      },
      {
        "platform":"bitfinex",
        "sellratio": 0.13,
        "buyratio": 0.24
      }
    ]
  },
  "cnyusdratio":6.45
}
  ```

## 汇率设置接口

用于页面汇率的设置，此处始终为人民币对外币的汇率，即1元人民币对等值的多少外币，如  {"cnyusd":0.1526},代表1元人民币等于0.1526美元。
所有数值为浮点型，四舍五入保留4位小数。

    URL:  http://52.41.94.237/rusher/settings/exchange_rate
    HTTP POST
  
  Request
  
  ```json
{
  "exchangerate":[
    {"cnyusd":0.1526},
    {"cnyjyp":16.8100}
  ]
}
  ```
  
  Response
  ```json
{
  "status":"success"
}
  ```

  
## 汇率获取接口


用于前端页面汇率的获取，此处始终为人民币对外币的汇率，即1元人民币对等值的多少外币，如  {"cnyusd":0.1526},代表1元人民币等于0.1526美元。
所有数值为浮点型，四舍五入保留4位小数。

    URL:  http://52.41.94.237/rusher/settings/exchange_rate
    HTTP GET
  
  Response
  
  ```json
{
  "exchangerate":[
    {"cnyusd":0.1526},
    {"cnyjyp":16.8100}
  ]
}
  ```
  
## 设置是否自动化

用于前端设置是否需要自动化
自动化：指的是后端达到设定的阈值后自动触发交易
半自动化：指的是后端达到设定的阈值后手动触发交易

"auto":true   代表需要自动化，反之代表不需要自动化

    URL:  http://52.41.94.237/rusher/settings/auto
    HTTP POST
  
  Request
  
  ```json
{
  "auto":true
}
  ```
   
  Response
  ```json
{
  "status":"success"
}
  ```

## 设置阈值

用于前端设置触发报警的阈值,阈值表示为百分比，四舍五入保留两位小数。
maxbuy_krakensell : 国内最大买一／kraken卖一
maxsell_krakenbuy : 国内最大卖一／kraken买一
maxbuy_bitbinexsell : 国内最大买一／bitbinex卖一
maxsell_bitbinexbuy : 国内最大卖一／bitbinex买一
maxbuy_minsell : 国内最大买一／国内最小卖一
  
    URL:  http://52.41.94.237/rusher/settings/threshold
    HTTP POST
  
  Request
  
  ```json
{
  "maxbuy_krakensell":1.80,
  "maxsell_krakenbuy":1.73,
  "maxbuy_bitfinexsell":2.12,
  "maxsell_bitfinexbuy":2.13,
  "maxbuy_minsell":1.98
}
  ```
   
  Response
  ```json
{
  "status":"success"
}
  ```
 


## 获取系统中已经设置的阈值

用于前端获取触发报警的阈值, 阈值表示为百分比，四舍五入保留两位小数。
maxbuy_krakensell : 国内最大买一／kraken卖一
maxsell_krakenbuy : 国内最大卖一／kraken买一
maxbuy_bitbinexsell : 国内最大买一／bitbinex卖一
maxsell_bitbinexbuy : 国内最大卖一／bitbinex买一
maxbuy_minsell : 国内最大买一／国内最小卖一
  
    URL:  http://52.41.94.237/rusher/settings/threshold
    HTTP Get
  
  Response
  
  ```json
{
  "maxbuy_krakensell":1.80,
  "maxsell_krakenbuy":1.73,
  "maxbuy_bitfinexsell":2.12,
  "maxsell_bitfinexbuy":2.13,
  "maxbuy_minsell":1.98
}
  ```


# Error 定义

## Error Response

所有的请求中如果发生错误，则会返回如下定义的错误消息
   
  ```json
{
  "status":"failed",
  "error":{
      "code": "system",
      "message": "can not set the exchange rate due to database error."
   }  
}

```

## Error Code

1. System            : 内部系统错误，数据库等
2. Unknown           : 无法确认的错误类型
3. NetWorkError      : 由于网络原因发生的错误
4. RemoteServiceDown : 第三方服务不可用
5. RemoteServiceError: 第三方服务返回的错误


## Error Message

具体的错误描述信息



# 其他业务知识记录

## Kraken
USD：美元 ETH：以太坊 xbt：比特币

首字母含义
X：代表数字货币
Z：现实货币，如人民币，美元，加拿大币等

"BCH": "0",
"DASH": "0",
"EOS": "0",
"GNO": "0",
"KFEE": "0",
"USDT": "0",
"XDAO": "0",
"XETC": "0",
"XETH": "0",
"XICN": "0",
"XLTC": "0",
"XMLN": "0",
"XNMC": "0",
"XREP": "0",
"XXBT": "0",
"XXDG": "0",
"XXLM": "0",
"XXMR": "0",
"XXRP": "0",
"XXVN": "0",
"XZEC": "0",
"ZCAD": "0",
"ZEUR": "0",
"ZGBP": "0",
"ZJPY": "0",
"ZKRW": "0",
"ZUSD": "0"