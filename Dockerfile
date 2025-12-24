FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app


COPY pom.xml .
COPY src ./src

RUN mvn clean package

FROM tomcat:11.0.4-jdk17

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY target/web1.war /usr/local/tomcat/webapps/web1.war

EXPOSE 8080
CMD ["catalina.sh", "run"]