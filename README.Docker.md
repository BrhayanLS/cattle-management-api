# AdGan - Sistema de Gestión de Ganado con Docker

## Descripción
Sistema completo de gestión de ganado que incluye backend en Spring Boot, frontend en Angular y base de datos MySQL, todo containerizado con Docker.

## Arquitectura del Sistema

### Servicios Docker
1. **db** - Base de datos MySQL 8.0
2. **backend** - API REST en Spring Boot
3. **db-init** - Inicialización de datos
4. **frontend** - Aplicación Angular

### Orden de Ejecución
1. **Base de datos** se inicia primero
2. **Backend** espera a que la BD esté lista y crea las tablas
3. **db-init** espera al backend y inserta datos iniciales
4. **Frontend** espera a que los datos estén listos

## Requisitos Previos

- Docker Desktop instalado y ejecutándose
- Docker Compose
- Al menos 4GB de RAM disponible

## Instrucciones de Uso

### 1. Clonar el Repositorio
```bash
git clone <url-del-repositorio>
cd AdGan-Back
```

### 2. Ejecutar el Sistema Completo
```bash
# Construir e iniciar todos los servicios
docker-compose up --build

# O ejecutar en segundo plano
docker-compose up --build -d
```

### 3. Verificar el Estado de los Servicios
```bash
# Ver logs de todos los servicios
docker-compose logs

# Ver logs de un servicio específico
docker-compose logs backend
docker-compose logs db-init
docker-compose logs frontend
```

### 4. Acceder a la Aplicación
- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **Base de datos**: localhost:3306

### 5. Detener el Sistema
```bash
# Detener todos los servicios
docker-compose down

# Detener y eliminar volúmenes (cuidado: elimina datos)
docker-compose down -v
```

## Estructura de Datos Iniciales

### Roles Disponibles
- **ADMIN** (ID: 1) - Acceso total al sistema
- **USER** (ID: 2) - Acceso a operaciones básicas
- **INVITED** (ID: 3) - Solo lectura de resúmenes

### Usuarios Predefinidos
- **Admin**: admin@mail.com / password
- **User**: user@mail.com / password
- **Invited**: invited@mail.com / password

## Solución de Problemas

### Problema: Frontend no carga
```bash
# Verificar logs del frontend
docker-compose logs frontend

# Reconstruir solo el frontend
docker-compose build frontend
docker-compose up frontend
```

### Problema: Backend no conecta a la BD
```bash
# Verificar logs del backend
docker-compose logs backend

# Verificar estado de la BD
docker-compose logs db
```

### Problema: Datos iniciales no se insertan
```bash
# Verificar logs de inicialización
docker-compose logs db-init

# Reiniciar solo la inicialización
docker-compose restart db-init
```

### Problema: Puerto ya en uso
```bash
# Verificar qué está usando el puerto
netstat -ano | findstr :4200
netstat -ano | findstr :8080
netstat -ano | findstr :3306

# Detener servicios que usen esos puertos
```

## Comandos Útiles

### Desarrollo
```bash
# Ver logs en tiempo real
docker-compose logs -f

# Ejecutar comandos dentro de un contenedor
docker-compose exec backend bash
docker-compose exec frontend sh
docker-compose exec db mysql -u admin -p

# Reconstruir un servicio específico
docker-compose build backend
docker-compose build frontend
```

### Mantenimiento
```bash
# Limpiar recursos no utilizados
docker system prune

# Ver uso de recursos
docker stats

# Backup de la base de datos
docker-compose exec db mysqldump -u admin -padmin adgan > backup.sql
```

## Configuración de Variables de Entorno

### Backend
- `SPRING_DATASOURCE_URL`: URL de conexión a la BD
- `SPRING_DATASOURCE_USERNAME`: Usuario de la BD
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de la BD

### Frontend
- `NODE_ENV`: Entorno de desarrollo
- `CHOKIDAR_USEPOLLING`: Para hot-reload en Windows

### Base de Datos
- `MYSQL_ROOT_PASSWORD`: Contraseña root
- `MYSQL_USER`: Usuario de la aplicación
- `MYSQL_PASSWORD`: Contraseña del usuario
- `MYSQL_DATABASE`: Nombre de la base de datos

## Notas Importantes

1. **Primera ejecución**: La primera vez puede tardar más tiempo debido a la descarga de imágenes y construcción de contenedores.

2. **Datos persistentes**: Los datos de la base de datos se mantienen entre reinicios. Para empezar desde cero, usar `docker-compose down -v`.

3. **Hot-reload**: El frontend tiene hot-reload configurado para desarrollo.

4. **Logs**: Siempre revisar los logs si hay problemas, especialmente los de `db-init` para verificar que los datos se insertaron correctamente.

## API Endpoints Principales

### Autenticación y Usuarios
- `GET /owner/all` - Listar todos los usuarios
- `POST /owner/save` - Crear nuevo usuario
- `PUT /owner` - Actualizar usuario
- `DELETE /owner/{id}` - Eliminar usuario

### Gestión de Ganado
- `GET /cattle/all` - Listar todo el ganado
- `GET /cattle/cattles` - Listar ganado activo
- `POST /cattle` - Crear nuevo ganado
- `PUT /cattle/update` - Actualizar ganado

### Ventas
- `GET /sale/all` - Listar todas las ventas
- `POST /sale/save` - Crear nueva venta

### Utilidades
- `GET /enums/roles` - Listar roles disponibles 