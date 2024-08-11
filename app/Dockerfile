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

# Копируем собранный JAR файл из предыдущей стадии
COPY --from=build /app/target/recruiting-0.0.1-SNAPSHOT.jar recruiting-0.0.1-SNAPSHOT.jar

# Открываем порт 8080
EXPOSE 8080

# Устанавливаем команду для запуска JAR файла
ENTRYPOINT ["java","-jar","recruiting-0.0.1-SNAPSHOT.jar"]