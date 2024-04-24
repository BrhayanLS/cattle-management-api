FROM maven:3.9.6-eclipse-temurin-17 as maven-builder

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean install -DskipTests

#FROM mysql

#COPY src/main/resources/db/migration/zviews.sql /docker-entrypoint-initdb.d
#COPY db-entrypoint.sh /
#RUN chmod +x /db-entrypoint.sh

#ENTRYPOINT ["/entrypoint.sh"]

FROM eclipse-temurin:17

COPY --from=maven-builder /app/target/adgan-0.0.1-SNAPSHOT.jar /app-service/adgan.jar

WORKDIR /app-service

ENV DATABASE_URL jdbc:mysql://roundhouse.proxy.rlwy.net:57173/adgan
ENV DATABASE_USERNAME root
ENV DATABASE_PASSWORD JPKecxOwNkvDxUasualZoakMinWRmQqV

EXPOSE 8080

CMD ["java", "-jar", "adgan.jar"]
