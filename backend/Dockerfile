FROM maven:3.9.6-eclipse-temurin-17 as maven-builder

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean install -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=maven-builder /app/target/adgan-0.0.1-SNAPSHOT.jar /app-service/adgan.jar
WORKDIR /app-service
ENTRYPOINT ["java", "-jar", "adgan.jar"]