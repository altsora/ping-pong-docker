FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /home/ping-service

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

FROM openjdk:11-jre-slim
#ENV PONG_SERVICE_URL "http://pong-service-container:7082/pong"
ENTRYPOINT ["java", "-jar", "/app/ping-service.jar"]

WORKDIR /app
COPY --from=build /home/ping-service/target .