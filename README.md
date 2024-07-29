# Star Wars API Viewer

Este proyecto es una aplicación web que consume la API de Star Wars (https://swapi.dev/) y muestra la información de personas y naves estelares. La aplicación permite buscar y ordenar los datos mostrados en una tabla en orden ascendente y descendente. La aplicación está desarrollada en Java utilizando Spring Boot y se despliega en un contenedor Docker.

## Requisitos

Para ejecutar este proyecto, necesitas tener instalados los siguientes software:

- [Java 22 JDK](https://jdk.java.net/22/)
- [Gradle 7.x](https://gradle.org/install/)
- [Docker](https://www.docker.com/products/docker-desktop)

## Instalación

### Paso 1: Clonar el Repositorio

Clona el repositorio de GitHub en tu máquina local:

```sh
git clone https://github.com/tu_usuario/StarWarsAPI.git
cd StarWarsAPI
```
### Paso 2: Instalar Gradle
choco install gradle

### Paso 3: Construir el proyecto con ggradle

Una vez que Gradle esté instalado, navega al directorio del proyecto y ejecuta el siguiente comando:

```sh
./gradlew clean build
```

### Paso 4: Instalar, construir y ejecutar docker

Primero debes tener docker instalado en tu sistema con el link de arriba.

Después, construyes la imagen de docker:

```sh
docker build -t starwarsapi .
```

inalmente, ejecutar el contenedor docker en el puerto 6969:

```sh
docker run -p 6969:6969 starwarsapi
```

### Paso 5: Acceder a la aplicación

Para ello tienes que poner en el buscador:

```sh

http://localhost:6969/people para poder acceder a la tabla de las personas de star wars y a
http://localhost:69697starships para acceder a la tabla de las naves de Star Wars

