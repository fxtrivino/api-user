# Spring Boot - API Usuarios

Esta es una aplicación Java / Maven / Spring Boot (version 1.0.0) basica para creacion, actualización, consulta y eliminación de usuarios.

## Ejecutar la aplicacion localmente

* Clona el repositorio
* Asegurate de usar JDK 1.8 o superior y Maven 3.x
* Hay algunas maneras de ejecutar la aplicacion Spring Boot localmente. Una manera es ejecutar el metodo `main` de com.bci.ApiUserApplication desde un IDE.
* Alternativamente puedes usar [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) de esta manera

```shell
mvn spring-boot:run
```
Una vez que la aplicacion se ejecuta visualizaras algo asi:

```
2025-03-14T07:05:01.314-05:00 INFO [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2025-03-14T07:05:01.324-05:00 INFO [           main] com.bci.ApiUserApplication               : Started ApiUserApplication in 2.276 seconds (process running for 2.587)
```

### Crear un ususario

```
POST /users
Accept: application/json
Content-Type: application/json

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "A23tara20$",
    "phones": [
            {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
            },
            {
            "number": "994373861",
            "citycode": "12",
            "contrycode": "58"
            }
    ]
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/users
```

### Consultar la lista de usuarios

```
http://localhost:8080/users

Response: HTTP 200
```

### Consultar usuario por id

```
http://localhost:8080/users/91dc6937-b72d-4a15-9e63-76b62a12f117

Response: HTTP 200
```

### Actualizar un usuario

```
PUT /users/91dc6937-b72d-4a15-9e63-76b62a12f117
Accept: application/json
Content-Type: application/json

{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "A23tara20$",
    "phones": [
            {
            "number": "1234568",
            "citycode": "1",
            "contrycode": "57"
            },
            {
            "number": "994373862",
            "citycode": "12",
            "contrycode": "58"
            }
    ]
}

RESPONSE: HTTP 200
```
### Para visualizar Swagger 2 API docs

Ejecuta en el navegador http://localhost:8080/swagger-ui/index.html

![swagger](https://github.com/user-attachments/assets/913e8f99-c8b5-480a-bf01-03920d5763cf)

### Para visualizar la base H2 en memoria

Ejecutar en el navegador 
To view and query the database you can browse to http://localhost:8080/h2-console. Ususario default es 'sa' con password en blanco.

# Arquitectura de la Aplicacion

![API-User drawio (2)](https://github.com/user-attachments/assets/b742341b-8c56-4fb1-b4a6-a97aff48049b)


# Preguntas y comentarios: fxtrivino@gmail.com
