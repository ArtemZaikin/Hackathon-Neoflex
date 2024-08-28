# Приложение для организации собеседований

Сервис Встречкин, функционал которого заключается в заведении и отображении встреч в календаре для проведения собеседований.

# Информация по системному анализу

Информация находится в каталоге [Каталог](https://git.codenrock.com/neo-hack-2024-1232/cnrprod1722867596-team-74124/razrabotka-rabochego-instrumenta-dlya-sinhronizacii-ekspertov-rekrutyorov-i-kandidatov-5883/-/tree/develop/reports?ref_type=heads)

# Запуск приложения для тестирования
## Требования
Установленный maven

## Запуск
1) Скачайте проект из ветки master
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
