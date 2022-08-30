FROM maven:3.6.1-jdk-11-slim AS build
WORKDIR /home/pong-service

COPY pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY src ./src
RUN mvn verify

FROM openjdk:11-jre-slim
ENTRYPOINT ["java", "-jar", "/app/pong-service.jar"]

WORKDIR /app
COPY --from=build /home/pong-service/target .