# Usa una imagen base de OpenJDK 22
FROM openjdk:22-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY build/libs/StarWarsAPI-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 6969

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
