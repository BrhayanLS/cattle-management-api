#!/bin/sh

# Esperar a que la base de datos esté lista
echo "Esperando a que la base de datos esté lista..."
while ! nc -z db 3306; do
  sleep 1
done
echo "Base de datos lista!"

# Configurar la URL de la base de datos
export SPRING_DATASOURCE_URL="jdbc:mysql://db:3306/adgan?createDatabaseIfNotExist=true"

# Iniciar la aplicación
echo "Iniciando la aplicación Spring Boot..."
exec java -jar app.jar
