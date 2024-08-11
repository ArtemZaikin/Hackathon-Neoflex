# Stage 1: Build the application using Maven
FROM maven:3.8.3-openjdk-17 AS build

LABEL maintainer="Aleksei Petrov <peta0982@gmail.com>"

# Устанавливаем рабочую директорию в /app
WORKDIR /app

# Копируем содержимое директории app в контейнер
COPY ./app /app

# Запускаем Maven для сборки проекта
RUN mvn -Dmaven.test.failure.ignore=true clean package

# Stage 2: Create the final image
FROM openjdk:17-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранный JAR файл из предыдущей стадии
COPY --from=build /app/target/recruiting-0.0.1-SNAPSHOT.jar recruiting-0.0.1-SNAPSHOT.jar

# Создаем директорию для сертификатов
RUN mkdir -p /etc/ssl/certs

# Копируем SSL сертификат из папки app в контейнер
COPY app/client.crt /etc/ssl/certs/client.crt

# Устанавливаем переменные окружения для подключения к базе данных
ENV DB_HOST=rc1b-wwbzmz7xcyzdmat1.mdb.yandexcloud.net
ENV DB_PORT=6432
ENV DB_NAME=cnrprod1722867596-team-74124
ENV DB_USERNAME=iequ6lug2bohXeocu5fao5aeghi6pohZ
ENV DB_SSL_MODE=verify-full
ENV DB_SSL_CERT=/etc/ssl/certs/client.crt

# Открываем порт 8080
EXPOSE 8080

# Устанавливаем команду для запуска JAR файла
ENTRYPOINT ["java", "-jar", "recruiting-0.0.1-SNAPSHOT.jar"]

