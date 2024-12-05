FROM openjdk:21-jdk-slim

WORKDIR /app

ARG JAR_FILE=target/api-gateway-*.jar
COPY ${JAR_FILE} api-gateway.jar

EXPOSE 8765

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]
