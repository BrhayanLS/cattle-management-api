FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM mysql

#COPY src/main/resources/db/migration/zviews.sql /docker-entrypoint-initdb.d
#COPY db-entrypoint.sh /
#RUN chmod +x /db-entrypoint.sh

#ENTRYPOINT ["/entrypoint.sh"]
