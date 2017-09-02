#!/bin/bash
echo  "Please notice this is a temporary Test Server:52.41.94.237."
echo ""

    echo "The version number should be the same with the version in your pom.xml file."
    read -p "Please enter your current version: " version
    mvn clean package -P prd
    scp rusher/target/rusher-prd-$version-SNAPSHOT.war upload@52.41.94.237:~/
    ssh upload@52.41.94.237 << eeooff
        sudo -i
        rm -rf /usr/local/bak/rusher
        mv /usr/local/webapps/rusher /usr/local/bak
        mkdir /usr/local/webapps/rusher
        cd /usr/local/webapps/rusher
        unzip /home/upload/rusher-prd-$version-SNAPSHOT.war -d  /usr/local/webapps/rusher
        chown derby.derby -R *  /usr/local/webapps/rusher
        cp /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/sample_jk /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/rusher.conf
        sed -i 's/sample/rusher/g' /usr/local/platform/apache-2.2.31/conf/jk/jkmounts/rusher.conf;/usr/local/platform/apache/bin/apachectl restart
        sudo /usr/local/platform/tomcat/bin/restartmd ; tail /usr/local/logs/catalina.out -f
eeooff