# Приложение для организации собеседований "Встречкин"

Сервис, функционал которого заключается в заведении и отображении встреч в календаре для проведения собеседований.

# Информация от аналитиков

- [Брифинг с заказчиком](https://drive.google.com/file/d/1W8DdunaPcFoyddwThkOCOoPeKd00REsO/view?usp=sharing)
- [Техническое задание](https://drive.google.com/file/d/1wO06ohpCW98RW6wI5abmIcsQMKbk3Xas/view?usp=sharing)

# Запуск приложения для тестирования
## Требования
Установленный maven

## Запуск
1) Скачайте проект (app) из ветки master
2) в командной строке (cmd/bash) перейдите в "КаталогЗагрузки/app"

windows: `cd C:\Users\user\Downloads\<КаталогЗагрузки>\app`

linux: `cd ~/Downloads/<КаталогЗагрузки>/app`

3) Запустите проект:

windows: `mvnw.cmd spring-boot:run`

linux: `./mvnw spring-boot:run`

## Подлючение
- Swagger UI [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- Консоль БД (h2) [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    - JDBC URL: jdbc:h2:mem:testdb
    - login: sa
    - password: password
    
postgresql/operations/connect#get-ssl-cert).

# Результаты тестирования

Отчет о результатах тестирования: [https://drive.google.com/drive/folders/1h23hYD5GqKMugviQxok6dyNNVbRjOIne?usp=sharing](https://drive.google.com/drive/folders/1h23hYD5GqKMugviQxok6dyNNVbRjOIne?usp=sharing)
