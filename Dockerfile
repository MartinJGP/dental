# Definir la etapa de construcción
FROM eclipse-temurin:17-jdk as build

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar maven para construir el proyecto
RUN mvn clean package

# Iniciar una nueva etapa para la imagen final
FROM openjdk:17-jdk-alpine

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa de construcción
COPY --from=build /app/target/SpringSecurityJWT-0.0.1-SNAPSHOT.jar /app/SpringSecurityJWT-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/SpringSecurityJWT-0.0.1-SNAPSHOT.jar"]