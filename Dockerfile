FROM openjdk:8

EXPOSE 8080
ADD target/bumbleBee.war bumbleBee.war
ENTRYPOINT ["java","-war","/spiceShop-0.0.1-SNAPSHOT.jar"]

ARG APP_NAME=spiceshop