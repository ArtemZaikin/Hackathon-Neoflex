# Приложение для организации собеседовании

Сервис, функционал которого заключается в заведении и отображении встреч в календаре для проведения собеседований.

# Запуск приложения для тестирования
## Требования
Установленный maven

## Запуск
1) Скачайте проект из ветки develop
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
    
# Информация для DevOps
Деплой приложения осуществляется в кластер Kubernetes

### Kubernetes
На вашу команду выделен kubernetes namespace. Для подключения к нему используйте утилиту `kubectl` и `*.kube.config` файл, который вам выдадут организаторы.

Состояние namespace, работающие pods и логи приложений можно посмотреть по адресу [https://dashboard.nh2024.codenrock.com/](https://dashboard.nh2024.codenrock.com/). Для открытия дашборда необходимо выбрать авторизацию через Kubeconfig и указать путь до выданного вам `*.kube.config` файла

### База данных
На каждую команду созданы базы данных Postgres. Доступы (login, password и db_name) одинаковые для обеих БД и выдаются на каждую команду организатором.

Для подключения к Postgres используйте следующую команду:
```
psql "host=rc1b-wwbzmz7xcyzdmat1.mdb.yandexcloud.net \
      port=6432 \
      sslmode=verify-full \
      dbname=$DB_NAME \
      user=$DB_USERNAME \
      target_session_attrs=read-write"
```
`rc1b-wwbzmz7xcyzdmat1.mdb.yandexcloud.net` - адрес хоста в кластере Yandex.Cloud. Подробнее в [документации](https://cloud.yandex.ru/docs/managed-postgresql/). Не забудьте скачать и установить [SSL сертификат](https://cloud.yandex.ru/docs/managed-postgresql/operations/connect#get-ssl-cert).

### Дополнительная информация
в неймспейсе имеются следующие секреты:
- regcred - секрет для пуллинга имиджей из репозитория Gitlab вашего проекта
- nh2024-tls - секрет с wildcard сертификатом для https для домена *.nh2024.codenrock.com. Указывается в ingress. Для ингрессов рекомендуется использовать домены четвертого уровня по имени команды
