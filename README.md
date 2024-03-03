
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

![Base De Datos](https://lh3.googleusercontent.com/pw/AP1GczNZ6JysaryqydfSYsLcGohq00X_pKWDqV14ZpiqqxUiG0PjCETOKjgl-QnY61aPirddJRl8pqPyDvUYuzAW9Gjq_1f3k3sJhCXJTjNBrUkLri9OVga2cu24uBhPTxdwOwbyDM-xHpwU6ZYJhQp-zCqPmJF8JWb2z46VMLhTF4H4B_oWop2D5q3C7mgSaSOPqzx2PDck8KkeW3u2DQ1KPYkZJcPDkjeWcpdg6Ge7eRBTPy4VZcGFWoB0VP6CPAd89zaaOjqeHLVTGW5bd0y0uFEwsl-RU3IkrfeXFjnGu75QBf8uBbSD0rwkM6AbMgBfyqx2Q83XTKhIuwiB57qWr8WoVeKwnLeDfd4Is7SEFqZ-nYPmqN-SbXp3v1Vi3e7pMxxFyJma-o3WlSFJKNBhs9TOofWZ1jC8xwD86_35HQKiR3VLhoAk_YJfSTtFfVsOzKzbcLAMdyzpZSgnve1Bh7iCbjbdunao43bUyNT08C2kYSTjWcrd7qEmMJsN1FKWXplYff5zg-aLS6esc0iADT0X2HoRtddeaLav3v5NglFlSxjpWwxUL5CTg1t4NEPRgkMxix3L4XENnxbSulgWxYIi5c0-clC-paXwEYralNJPz3yYxJPgtaGQ8-Mf3fYDGKMmCg0VC1CyeW9dhQhhZ-B0sY2k7iA7jzO6TxknlDRQEHnSRGTLNlxqtyOCwazujSwklHlNAzQcDAS_rZHFG2wr9xXFWOABfymZyo8S_nE5_vDK5pKIxBsdSaiw53NEul-QfOx6PPBLjKCwxZ5kb2D7Yi-RHqPSSPFo40hi48zJoBC6DQNIWVXuo1t5J-pJhHglPKCypsZFVB1-9A66eiV2qqOmjGhPus0Hozpwp1GazAQJSTimaYDu6mvIQboGO3AVWu66PMzqYNB-vn9cMdsX_iAFLoPs-0gfUbnZXpglM9JYWppxwCYVJ9vWb2Wt_mAvPGWFaim59SbvK37wfaYauLCSMsacLqjsf80KfhTcaz_8bcbUa0OtVJ7OvgzVeCQEa9OozE9nL3qD1JJNE0W06lZABCmxJ-hFBQ8=w705-h393-s-no-gm?authuser=0)

Ejemplo de creacion de usuarios. Hay tres roles posibles: ADMIN, USER e INVITED

![Created Owner](https://drive.google.com/file/d/1WAhqP5MOQT283NEFxUIKpGSREVMx1qAd/view?usp=sharing)

Ejemplo de logueo y generacion de token de autenticacion

![Login](https://drive.google.com/file/d/1U8BnPgRT4T_DKxQYjlRk56SUn276pXBi/view?usp=sharing)

Para los demas EndPoints se debe autenticar primero, para esto seleccionamos "Authorization" en "Type" seleccionamos "Bearer Token" y en el recuadro de la derecha pegamos el token que obtuvimos con anterioridad

![Autenticacion](https://drive.google.com/file/d/1iuLZs6ZKLxQ5OC2HA18IsBJRqu7GSJtc/view?usp=sharing)

Estando autenticados podemos hacer las solicitudes

![Prueba Cattles](https://drive.google.com/file/d/1O7ze3--KZmB8MoQAB5AqiR2Y9PfSYLAU/view?usp=sharing)

De lo contrario obtendremos errores 403 que significa que nuestro token no es valido y es necesario autenticarnos correctamente

![No Autorizado](https://drive.google.com/file/d/1AGEI76kbQiXEbaN2Idbw_ysEC19x43-8/view?usp=sharing)

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

