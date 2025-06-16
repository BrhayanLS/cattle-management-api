# API de Gestión Ganadera (AdGan) 🐄

## Descripción
AdGan es una API REST desarrollada en Java con Spring Boot que proporciona una solución completa para la gestión de fincas ganaderas. Permite el manejo de inventario de ganado, registro de ventas, gestión de usuarios y seguimiento de operaciones diarias.

## Características Principales 🌟

### Gestión de Ganado
- Registro completo de animales (nombre, edad, peso, estado
- Historial de ventas por animal y costos asociados
- Cálculo automático de ganancias

### Sistema de Usuarios
- Múltiples roles (ADMIN, USER, INVITED)
- Autenticación JWT
- Gestión de permisos por rol
- Registro y actualización de perfiles

### Gestión de Ventas
- Registro detallado de transacciones
- Cálculo de precios por kilo
- Control de costos (báscula, camión)
- Generación de resúmenes financieros

## Arquitectura del Proyecto 🏗️

### Estructura de Paquetes
```
com.adgan
├── controller/     # Controladores REST
├── service/        # Lógica de negocio
│   └── dto/       # Objetos de transferencia de datos
├── persistence/    # Capa de persistencia
│   ├── entity/    # Entidades JPA
│   ├── repository/# Repositorios
│   └── projection/# Vistas de datos
└── config/        # Configuraciones
```

### Tecnologías Utilizadas 💻
- **Backend**: Java 17, Spring Boot 3.2.0
- **Base de Datos**: MySQL
- **ORM**: Spring Data JPA
- **Seguridad**: Spring Security + JWT
- **Documentación**: Swagger/OpenAPI
- **Gestión de Dependencias**: Maven

## Requisitos del Sistema 📋
- JDK 17 o superior
- MySQL 8.0 o superior
- Maven 3.6 o superior
- IDE recomendado: IntelliJ IDEA

## Configuración e Instalación 🚀

1. **Clonar el Repositorio**
   ```bash
   git clone https://github.com/BrhayanLS/adgan.git
   cd adgan
   ```

2. **Configurar Base de Datos**
   - Crear base de datos MySQL
   - Configurar `application.properties` con credenciales

3. **Compilar y Ejecutar**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Documentación de API 📚

### Endpoints Principales

#### Gestión de Ganado
- `GET /cattle/all` - Listar todo el ganado
- `GET /cattle/{id}` - Obtener ganado por ID
- `POST /cattle/save` - Registrar nuevo ganado
- `PUT /cattle/update` - Actualizar ganado
- `DELETE /cattle/{id}` - Eliminar ganado

#### Gestión de Usuarios
- `GET /owner/all` - Listar todos los usuarios
- `POST /owner/save` - Registrar nuevo usuario
- `PUT /owner/update` - Actualizar usuario
- `DELETE /owner/{id}` - Eliminar usuario

#### Gestión de Ventas
- `GET /sale/all` - Listar todas las ventas
- `POST /sale/save` - Registrar nueva venta
- `PUT /sale/update` - Actualizar venta
- `DELETE /sale/{id}` - Eliminar venta

## Seguridad y Permisos 🔒

### Roles y Permisos
| Rol     | Descripción                    |
|---------|--------------------------------|
| ADMIN   | Acceso total al sistema        |
| USER    | Acceso a operaciones básicas   |
| INVITED | Solo lectura de resúmenes      |

### Matriz de Permisos

| EndPoint           | ADMIN | USER | INVITED |
| ------------------ | ----- | ---- | ------- |
| All Owner       |  ✅   |      |         |
| Get By Id Owner |  ✅   |      |         |
| Save Owner      |       |       |         |
| Update Owner    |  ✅   |      |         |
| Delete Owner    |  ✅   |      |         |
| Owners          |  ✅   |      |         |
| All Cattle      |  ✅   |  ✅  |         |
| Cattles         |  ✅   |  ✅  |         |
| Get By Id Cattle|  ✅   |  ✅  |         |
| Cattles Resume  |  ✅   |  ✅  |    ✅   |
| Sold            |  ✅   |  ✅  |         |
| Save Cattle     |  ✅   |  ✅  |         |
| Update Cattle   |  ✅   |      |         |
| Delete Cattle   |  ✅   |      |         |
| Dead Cattle     |  ✅   |  ✅  |         |
| All Sales       |  ✅   |      |         |
| Sales           |  ✅   |  ✅  |         |
| Get By Id Sale  |  ✅   |  ✅  |         |
| Save Sale       |  ✅   |      |         |
| Update Sale     |  ✅   |      |         |
| Delete Sale     |  ✅   |      |         |
| Login           |       |      |         |

## Contribución 🤝
1. Fork el proyecto
2. Crear rama para feature (`git checkout -b feature/AmazingFeature`)
3. Commit cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir Pull Request

## Autor 👨‍💻
- **BrhayanLS** - [GitHub](https://github.com/BrhayanLS)

## Licencia 📄
Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.

## Contacto 📧
- GitHub: [@BrhayanLS](https://github.com/BrhayanLS)
- Email: [brhayanls10@gmail.com]

---
⭐️ Si te gusta el proyecto, no olvides darle una estrella en GitHub
