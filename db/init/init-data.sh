#!/bin/bash

echo "Iniciando proceso de inicialización de datos..."

# Esperar a que la base de datos esté lista
echo "Esperando a que la base de datos esté lista..."
while ! mysqladmin ping -h db -u admin -padmin --silent; do
  echo "Base de datos no está lista, esperando..."
  sleep 5
done

echo "Base de datos lista!"

# Esperar un tiempo fijo para que el backend se inicie completamente
echo "Esperando a que el backend se inicie completamente..."
sleep 60

# Verificar que las tablas existan antes de insertar datos
echo "Verificando que las tablas existan..."
if mysql -h db -u admin -padmin adgan -e "SHOW TABLES LIKE 'roles';" | grep -q "roles"; then
  echo "Tabla roles encontrada, procediendo con la inserción de datos..."
  
  # Ejecutar el script de datos iniciales
  echo "Ejecutando script de datos iniciales..."
  mysql -h db -u admin -padmin adgan < /docker-entrypoint-initdb.d/V1__datos-iniciales.sql
  
  if [ $? -eq 0 ]; then
    echo "Datos iniciales insertados correctamente!"
  else
    echo "Error al insertar datos iniciales"
    exit 1
  fi
else
  echo "Tabla roles no encontrada, esperando más tiempo..."
  sleep 30
  
  # Intentar de nuevo
  if mysql -h db -u admin -padmin adgan -e "SHOW TABLES LIKE 'roles';" | grep -q "roles"; then
    echo "Tabla roles encontrada en el segundo intento, procediendo..."
    mysql -h db -u admin -padmin adgan < /docker-entrypoint-initdb.d/V1__datos-iniciales.sql
  else
    echo "Error: No se pudo encontrar la tabla roles después de múltiples intentos"
    exit 1
  fi
fi

echo "Proceso de inicialización completado!" 