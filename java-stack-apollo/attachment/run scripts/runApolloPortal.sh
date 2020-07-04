#!/bin/sh

url="localhost:3306"
username="root"
password="123456"

java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dpro_meta=http://localhost:8081/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://${url}/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -jar apollo-portal-1.6.1.jar
