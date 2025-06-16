#!/bin/sh

# Set the JDBC URL (replace with your actual connection details)
JDBC_URL="jdbc:mysql://dbmysql:3306/adgan"  # Adjust useSSL as needed

# Wait for the MySQL database to be ready
while ! nc -z dbmysql:3306; do sleep 1; done

# Set environment variable for Hibernate (optional, but recommended)
export DATABASE_URL="$JDBC_URL"

# Start the API
exec java -jar /app-service/adgan.jar
