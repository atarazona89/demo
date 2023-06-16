# README

Este documento proporciona instrucciones para desplegar un proyecto de Spring Boot con Gradle en el terminal, considerando variables de entorno y una base de datos H2.

## Requisitos previos
Antes de comenzar, asegúrate de tener lo siguiente instalado en tu sistema:

- Java Development Kit (JDK) 8 o superior
- Gradle

## Pasos para el despliegue
Sigue los pasos a continuación para desplegar el proyecto de Spring Boot con Gradle:

- Clonar el proyecto: Inicia clonando el repositorio del proyecto en tu máquina local.
```bash
git clone https://github.com/atarazona89/demo.git
```

- Configurar variables de entorno:
El proyecto requiere algunas variables de entorno para su correcto funcionamiento. Asegúrate de configurar las variables requeridas antes de ejecutar la aplicación.
```bash
export HOME_DB=valor1 ## Directorio donde se almacenará la BD
## export HOST_DB=valor2 ## Host de la BD. Fue usado para pruebas
export PASSWORD_DB=pasword
export PROFILE=test
export USER_DB=user
export LOGGER_LEVEL=WARN ## Puede ser INFO, DEBUG, WARN, ERROR
```
(Reemplaza estos valores con los adecuados)

- Configurar base de datos H2: 
El proyecto utiliza una base de datos H2 integrada. Asegúrate de que los valores de acceso puedan acceder al sistema de archivos de tu computadora. Puedes ajustar las propiedades según tus necesidades.

### Compilar el proyecto: 
Entra al directorio raíz del proyecto y ejecuta el siguiente comando para compilar el proyecto utilizando Gradle.

```bash
gradle build
```
Esto compilará el proyecto y generará el archivo JAR de la aplicación en el directorio build/libs.

### Ejecutar la aplicación: 
Una vez que el proyecto se haya compilado correctamente, puedes ejecutar la aplicación utilizando el siguiente comando:
```bash
java -jar build/libs/demo-0.0.1-SNAPSHOT-plain.jar
```
Asegúrate de reemplazar nombre-del-proyecto con el nombre real del archivo JAR generado.

## Probar la API:
La aplicación ahora se está ejecutando y la API está disponible para su uso. Puedes realizar solicitudes a la API utilizando herramientas como cURL o Postman.
- Revisión de status:
```bash
curl -L -X GET 'localhost:8080/user/health'
```
- Crear usuario:
```bash
curl -L -X POST 'localhost:8080/user' \
-H 'Content-Type: application/json' \
--data-raw '{
    "name": "Alejandro",
    "email": "alejandro@miMail.com",
    "password": "Afoo2barbaz1",
    "phones": [
        {
            "number": 1234567878,
            "cityCode": 987,
            "countryCode": "CHL"
        }
    ]
}'
```

- Login:
```bash
curl -L -X POST 'localhost:8080/user/login' \
-H 'Content-Type: application/json' \
--data-raw '{
    "email": "alejandro@miMail.com",
    "password": "Afoo2barbaz1"
}'
```

## Tests
Los siguientes tests unitarios están incluidos en la aplicación:
- UserServiceTests: Contiene tests para los servicios de registro de usuarios y login.
- 
Para ejecutar los tests, ejecuta el siguiente comando:

```bash
gradle test
```


## ¡Felicidades! 
Has desplegado con éxito el proyecto de Spring Boot con Gradle en tu entorno local y puedes comenzar a utilizar la API. Asegúrate de ajustar los pasos según las necesidades y la configuración específica de tu proyecto.