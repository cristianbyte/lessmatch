# 1. Imagen con Java 21
FROM eclipse-temurin:21-jdk AS build

# 2. Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copia todo el código fuente al contenedor
COPY . .

# 4. Compila el proyecto (sin pruebas para que sea más rápido)
RUN ./mvnw clean package -DskipTests

# 5. Segunda etapa: imagen liviana para producción
FROM eclipse-temurin:21-jdk

# 6. Establece el directorio de trabajo final
WORKDIR /app

# 7. Copia el .jar compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# 8. Expone el puerto que usa Spring Boot (no obligatorio para Render, pero útil)
EXPOSE 8080

# 9. Comando que ejecuta la app
ENTRYPOINT ["java", "-jar", "app.jar"]
