#!/bin/sh

url="localhost:3306"
username="root"
password="123456"

java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDBPRO?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-configservice.log -Dserver.port=8081 -jar apollo-configservice-1.6.1.jar &
java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDBPRO?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-adminservice.log -Dserver.port=8091 -jar apollo-adminservice-1.6.1.jar
