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