
# API de Gestion Ganadera

La API de gestion ganadera "AdGan" es un proyecto backend contruido con Java y Spring Boot. Su objetivo es proporcionar una herramienta para que los ganaderos puedan hacer un seguimiento de los registros de sus fincas. A continuacion se detallan aspectos clasves del proyeto.
## Caracteristicas

- Manejo de los animales: Los usuarios pueden registrar los animales bovinos de la finca.
- Creacion de ususarios: Se pueden registrar los usuarios con diferentes niveles de acceso basado en el rol con el que fue creado.
- Registro de ventas: Los usuarios pueden registrar sus ventas del ganado y los costos asociados para registrar las ganacias netas.
- Operaciones CRUD: Todas las opciones de registro permiten Crear, Leer, Actualizar y Eliminar (No se elimina en la base de datos, se oculta el dato)
## Tecnologias

**Java:** Lenguaje principal del proyecto

**Spring Boot:** Simplifica la configuarcion y desarrollo de aplicaciones Java

**Spring Data JPA:** Facilita la interaccion con base de datos

**Spring Security con JWT:** Stack que permite proteger las rutas y generar tokens seguros para la autenticacion y autorizacion de usuarios

**MySQL:** Base de datos

**Maven:** Manejador de dependencias
## Screenshots

Construccion de la base de datos relacional y las vistas para consultas personalizadas

![Base De Datos](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Base%20de%20datos.png)

Ejemplo de creacion de usuarios. Hay tres roles posibles: ADMIN, USER e INVITED

![Created Owner](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Prueba%20SaveOwner.png)

Ejemplo de logueo y generacion de token de autenticacion

![Login](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Prueba%20Login.png)

Para los demas EndPoints se debe autenticar primero, para esto seleccionamos "Authorization" en "Type" seleccionamos "Bearer Token" y en el recuadro de la derecha pegamos el token que obtuvimos con anterioridad

![Autenticacion](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Autenticacion.png)

Estando autenticados podemos hacer las solicitudes

![Prueba Cattles](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/Prueba%20Cattles.png)

De lo contrario obtendremos errores 403 que significa que nuestro token no es valido y es necesario autenticarnos correctamente

![No Autorizado](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/Screenshots/No%20Autorizado.png)

## Deployment

Para su correcto funcionamiento debemos tener instalado el Java Development Kit en su version 17 ([JDK 17](https://adoptium.net/es/temurin/releases/?os=any&package=jdk&version=17)). Para la ejecucion se puede usar el IDE de su preferencia pero sigiero usar [IntelliJ](https://www.jetbrains.com/es-es/idea/download/?section=windows).

Para la base de datos en MySQL se deja un archivo sql con las vistas creadas y datos por defecto para solo ejecutar y hacer pruebas. El archivo lo podemos encontrar [aqui](https://github.com/BrhayanLS/adgan/tree/main/src/main/resources/db/migration).

En el archivo [aplication.properties](https://github.com/BrhayanLS/adgan/blob/main/src/main/resources/application.properties) debemos cambiar el usuario y contraseña de nuestra base de datos si es necesario.

Para realizar las pruebas recomiendo usar [Postman](https://www.postman.com/downloads/) ya que se dejara un archivo JSON para importar con todos los EndPoints y ejemplos de como llenar los formularios para las peticiones que lo requieran. El archivo lo podemos encontrar [aqui](https://github.com/BrhayanLS/adgan/tree/main/src/main/resources/postman).

Ejemplo de JSON usado en postman (Crear Usuario):
```bash
POST: http://localhost:8080/owner/save

  {
    "apellido": "Prueba",
    "contacto": "15468",
    "correo": "prueba@mail.com",
    "username" : "Prueba",
    "nombre": "Prueba",
    "password": "pass",
    "roles" : ["ADMIN"]
}
```

Ejemplo de JSON usado en postman (Crear Venta):
```bash
POST: http://localhost:8080/sale

  {
    "fechaVenta": "2023-10-21",
    "precioKilo": 8700,
    "valorCamion": 250000,
    "valorBascula": 110000,
    "saleCattles": [
        {
            "cattleId": 4,
            "peso": 654
        },
        {
            "cattleId": 6,
            "peso": 499
        }
    ]
}
```
## Distribucion de permisos por roles

| EndPoint| ADMIN | USER | INVITED |
| ------- | ----- | ---- | ------- |
| All Owners | X |  |  |
| Get By Id Owner | X |  |  |
| Save Owner |  |  |  |
| Update Owner | X |  |  |
| Delete Owner | X |  |  |
| Owners | X |  |  |
| All Cattle | X | X |  |
| Cattles | X | X |  |
| Get By Id cattle | X | X |  |
| Cattles Resume | X | X | X |
| Sold | X |  X|  |
| Save Cattle | X | X |  |
| Update Cattle | X |  |  |
| Delete Cattle | X |  |  |
| Dead Cattles | X | X |  |
| All Sales | X |  |  |
| Sales | X | X |  |
| Get By Id Sale | X | X |  |
| Save Sale | X |  |  |
| Update Sale | X |  |  |
| Delete Sale | X |  |  |
| Login |  |  |  |

## Authors

- [@BrhayanLS](https://github.com/BrhayanLS)

