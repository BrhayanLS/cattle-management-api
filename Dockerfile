FROM maven:3.9.6-eclipse-temurin-17 as maven-builder
COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM eclipse-temurin:17

COPY --from=maven-builder app/target/adgan-0.0.1-SNAPSHOT.jar /app-service/adgan.jar
WORKDIR /app-service

EXPOSE 8080

ENTRYPOINT ["java","-jar","adgan.jar"]