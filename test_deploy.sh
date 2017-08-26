#!/bin/bash
echo  "Please notice this is a temporary Test Server:35.164.34.250."
echo ""

    echo "The version number should be the same with the version in your pom.xml file."
    read -p "Please enter your current version: " version
    mvn clean package -P uat
    scp derbysoft-go/target/goplatform-uat-$version-SNAPSHOT.war upload@35.164.34.250:~/
    ssh upload@35.164.34.250 << eeooff
        sudo -i
        rm -rf /usr/local/bak/go
        mv /usr/local/webapps/go /usr/local/bak
        mkdir /usr/local/webapps/go
        cd /usr/local/webapps/go
        unzip /home/upload/goplatform-uat-$version-SNAPSHOT.war -d  /usr/local/webapps/go
        chown derby.derby -R *  /usr/local/webapps/go
        cp /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/sample_jk /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/go.conf
        sed -i 's/sample/go/g' /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/go.conf;/usr/local/platform/apache/bin/apachectl restart
        sudo /usr/local/platform/tomcat/bin/restartmd ; tail /usr/local/logs/catalina.out -f
eeooff