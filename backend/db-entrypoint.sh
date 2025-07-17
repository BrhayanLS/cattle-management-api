#!/bin/sh

# Espera a que la API esté lista
while ! nc -z api:8080; do sleep 1; done

# Crea las tablas de la API
java -jar /app-service/adgan.jar

# Ejecuta el script SQL
mysql -u root -p"$MYSQL_ROOT_PASSWORD" adgan < /docker-entrypoint-initdb.d/zviews.sql

# Inicia el servidor MySQL
exec "$@"
