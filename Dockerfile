# Используем официальный образ OpenJDK 21
FROM openjdk:21-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app
# Копируем скомпилированный JAR-файл в контейнер
COPY target/*.jar app.jar

# Указываем порт, который будет слушать Spring Boot
EXPOSE 8080

# Запускаем приложение
CMD ["java", "-jar", "app.jar"]