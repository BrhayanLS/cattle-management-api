# API de Gestión Ganadera (AdGan)

¡Bienvenido/a al API de Gestión Ganadera "AdGan"! Este proyecto backend, construido con Java y Spring Boot, está diseñado para ayudar a los ganaderos a gestionar los registros de sus fincas de una manera eficiente y segura.

## Características 🐄

- **Manejo de Animales:** Registra y administra los animales bovinos de tu finca.
- **Creación de Usuarios:** Registra usuarios con diferentes niveles de acceso basados en roles específicos.
- **Registro de Ventas:** Registra las ventas de ganado y sus costos asociados para calcular las ganancias netas.
- **Operaciones CRUD:** Permite crear, leer, actualizar y ocultar datos (en lugar de eliminarlos) en la base de datos.

## Tecnologías Utilizadas 💻

- **Java:** Lenguaje principal del proyecto.
- **Spring Boot:** Facilita la configuración y el desarrollo de aplicaciones Java.
- **Spring Data JPA:** Simplifica la interacción con la base de datos.
- **Spring Security con JWT:** Proporciona autenticación y autorización seguras para los usuarios.
- **MySQL:** Base de datos relacional.
- **Maven:** Manejador de dependencias.

## Capturas de Pantalla 📸

Aquí puedes ver algunos ejemplos visuales del proyecto:

- **Base de Datos:**
![Base De Datos](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Base%20de%20datos.png)

- **Creación de Usuarios:**
![Created Owner](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Prueba%20SaveOwner.png)

- **Autenticación y Generación de Tokens:**
![Login](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Prueba%20Login.png)

- **Ejemplo de Autenticación en Postman:**
![Autenticación](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Autenticacion.png)

## Implementación y Pruebas 🚀

Para ejecutar el proyecto:

1. Asegúrate de tener instalado [JDK 17](https://adoptium.net/es/temurin/releases/?os=any&package=jdk&version=17).
2. Recomiendo usar [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=windows) como IDE.
3. Configura las credenciales de tu base de datos en el archivo [application.properties](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/application.properties).
4. Para pruebas, utiliza [Postman](https://www.postman.com/downloads/) y descarga el archivo JSON de endpoints desde [aquí](https://github.com/BrhayanLS/adgan/tree/main/src/main/resources/postman).

## Distribución de Permisos por Roles 🔑

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

## Autor 👨‍💻

- [@BrhayanLS](https://github.com/BrhayanLS)

¡Gracias por utilizar AdGan para gestionar tu ganadería! Si tienes alguna pregunta o sugerencia, no dudes en ponerte en contacto con el autor.
