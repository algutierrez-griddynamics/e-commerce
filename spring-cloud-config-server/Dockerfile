FROM openjdk:21-jdk-slim

EXPOSE 8761

VOLUME /tmp

ARG JAR_FILE=target/spring-cloud-config-server-*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
