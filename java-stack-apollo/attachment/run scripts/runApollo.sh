#!/bin/sh

url="localhost:3306"
username="root"
password="123456"

java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-configservice.log -Dserver.port=8080 -jar apollo-configservice-1.6.1.jar &
java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-adminservice.log -Dserver.port=8090 -jar apollo-adminservice-1.6.1.jar & 
java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://%url%/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=%username% -Dspring.datasource.password=%password% -jar apollo-portal-1.6.1.jar &

# java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://localhost:3306/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=root -Dspring.datasource.password=123456 -jar apollo-portal-1.6.1.jar
