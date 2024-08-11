# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17 AS build

LABEL maintainer="Aleksei Petrov <peta0982@gmail.com>"

# Устанавливаем рабочую директорию в /app
WORKDIR /app

# Копируем содержимое директории app в контейнер
COPY ./app /app

# Сборка проекта
RUN mvn -Dmaven.test.failure.ignore=true clean package

# Stage 2: Create the final image
FROM openjdk:17-slim

# Создаем каталог для сертификатов
RUN mkdir -p /app/certs

# Копируем сертификат из папки app в папку certs внутри контейнера
COPY ./app/client.crt /app/certs/client.crt

# Копируем собранный JAR файл
COPY --from=build /app/target/recruiting-0.0.1-SNAPSHOT.jar /app/recruiting-0.0.1-SNAPSHOT.jar

# Устанавливаем переменные окружения для JVM
ENV JAVA_OPTS="-Djavax.net.ssl.trustStore=/app/certs/client.crt -Djavax.net.ssl.trustStorePassword=yourpassword"

# Открываем порт
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/recruiting-0.0.1-SNAPSHOT.jar"]

