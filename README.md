# Spring Boot - API Usuarios

Esta es una aplicación Java / Maven / Spring Boot (version 1.0.0) para creacion, actualización, consulta y eliminación de usuarios.

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
2025-03-14T07:05:01.314-05:00 INFO [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/userManagement/v1'
2025-03-14T07:05:01.324-05:00 INFO [           main] com.bci.ApiUserApplication               : Started ApiUserApplication in 2.276 seconds (process running for 2.587)
```

## Creación de las tablas en Base H2

Los scripts de creacion de las tablas se pueden visualizar en la consola

```
Hibernate: drop table if exists phones cascade 
Hibernate: drop table if exists usuarios cascade 
Hibernate: create table phones (id bigint generated by default as identity, user_id uuid not null, city_code varchar(255), country_code varchar(255), number varchar(255), primary key (id))
Hibernate: create table usuarios (is_active boolean, created timestamp(6) not null, last_login timestamp(6) not null, modified timestamp(6) not null, user_id uuid not null, email varchar(255), name varchar(255), password varchar(255), token varchar(255), primary key (user_id))
Hibernate: alter table if exists phones add constraint FK8o7nfry19rrku7s0itrgl16vm foreign key (user_id) references usuarios
```

### Creación de usuario - Response: HTTP 201 (Created)

```
curl -X 'POST' \
  'http://localhost:8080/userManagement/v1/users' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "A23tara20$",
    "phones": [
            {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
            },
            {
            "number": "994373861",
            "cityCode": "12",
            "countryCode": "58"
            }
    ]
}'
```

### Consultar la lista de usuarios - Response: HTTP 200 (OK)

```
http://localhost:8080/userManagement/v1/users
```

### Consultar usuario por id - Response: HTTP 200 (OK)

```
http://localhost:8080/userManagement/v1/users/91dc6937-b72d-4a15-9e63-76b62a12f117
```

### Actualización de usuario - Response: HTTP 200 (OK)

```
curl -X 'PUT' \
  'http://localhost:8080/userManagement/v1/users/9dda1a09-e15d-4543-a5b3-ba2003115167' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Juan Rodriguez",
    "email": "juan3@rodriguez.org",
    "password": "A23tara20$",
    "phones": [
            {
            "number": "1234590",
            "cityCode": "1",
            "countryCode": "57"
            },
            {
            "number": "994373876",
            "cityCode": "12",
            "countryCode": "58"
            }
    ]
}'
```

### Eliminación de usuario - Response: HTTP 204 (No Content)

```
curl -X 'DELETE' \
  'http://localhost:8080/userManagement/v1/users/ceec557c-ba25-46db-9df6-0729f86de188' \
  -H 'accept: */*'
```

### Para visualizar Swagger 2 API docs

Ejecutar en el navegador http://localhost:8080/userManagement/v1/swagger-ui/index.html

![Screenshot 2025-03-14 at 7 16 29 PM](https://github.com/user-attachments/assets/a79bb825-1c62-48e4-b274-f0d2184c3d37)


### Para visualizar la base H2 en memoria

Ejecutar en el navegador http://localhost:8080/userManagement/v1/h2-console, usuario default es 'sa' con password en blanco.

![Screenshot 2025-03-14 at 7 21 44 PM](https://github.com/user-attachments/assets/161a0089-e800-4ecd-b394-bd023b0daa97)


### Arquitectura de la Aplicacion

![API-User drawio (2)](https://github.com/user-attachments/assets/b742341b-8c56-4fb1-b4a6-a97aff48049b)


### Preguntas y comentarios: fxtrivino@gmail.com
