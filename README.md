# AccountManagementApp
### Реализация REST API сервиса по управлению аккаунтами пользователей.
### Описание:
Сервис разработан на основе Spring Framework, использовались: Spring Boot, Spring Data JPA, Hibernate, Spring Security, H2 Database (in-memory). <br>
Реализованы CRUD операции управления учетными данными пользователей. Все действия осуществляются через эндпоинты сервиса по протоколу HTTP, 
доступными по определенным URL.<br>
В системе присутствует набор ролей: "ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_USER", и набор статусов пользователя: "ACTIVE", "BLOCKED".<br>
### Модель данных:

<br>Пользователи (Users)<br>

| Наименование поля | Тип поля                      | Комментарий                                     |
|-------------------|-------------------------------|-------------------------------------------------|
| id                | UUID                          | Уникальный идентификатор пользователя в системе |
| E-mail/login      | Строка                        | E-mail пользователя                             |
| Фамилия           | Строка                        | Фамилия пользователя                            |
| Имя               | Строка                        | Фамилия пользователя                            |
| Отчество          | Строка                        | Отчество пользователя                           |
| Роль              | UUID                          | Идентификатор роли пользователя в системе       |
| Пароль            | Строка                        | Хеш пароля пользователя                         |
| Статус            | Фиксированные значения (Enum) | Текущий статус пользователя                     |
| Дата создания     | Дата и время                  | Дата и время создания пользователя              |

<br>Реализована валидация для пользователей по правилу:<br>

| Наименование поля | Правило валидации                                                                                                                                              |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| E-mail/login      | Поле должно содержать корректный E-mail адрес, поле является уникальным (не может быть 2-х пользователей с одним e-mail). Является обязательным для заполнения |
| Фамилия           | Может содержать только символы A-z, А-я, символ «-». Является обязательным для заполнения                                                                      |
| Имя               | Может содержать только символы A-z, А-я, символ «-». Является обязательным для заполнения                                                                      |
| Отчество          | Может содержать только символы A-z, А-я, символ «-». Может быть пустым                                                                                         |
| Роль              | Может содержать только идентификаторы существующих ролей. Является обязательным для заполнения                                                                 |
| Пароль            | Является обязательным для заполнения                                                                                                                           |
| Статус            | Принимает фиксированные значения статуса пользователя. Является обязательным для заполнения                                                                    |
| Дата создания     | Может содержать только валидную дату и время. Является обязательным для заполнения       

<br>Роли (Role)<br>

| Наименование поля | Тип поля | Комментарий                             |
|-------------------|----------|-----------------------------------------|
| Id                | UUID     | Уникальный идентификатор роли в системе |
| Наименование      | Строка   | Наименование роли                       |
| Описание          | Строка   | Описание роли                           |

<br>Реализована валидация для ролей по правилу:<br>

| Наименование поля | Правило валидации                                                                                                                                                              |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Наименование      | Может содержать только символы A-z, А-я, символы «-», « », «_», поле является уникальным (не может быть 2-х ролей с одним наименованием). Является обязательным для заполнения |
| Описание          | Может быть пустым  

### Endpoints:<br>
 - Создание пользователя<br>
Запрос: POST /users<br>
Метод запроса: POST<br>
URL сервиса: /users<br>
Query string параметры: отсутствуют<br>

Тело запроса: содержит объект формата JSON с параметрами:<br>
``` yaml
{
"email": "test@test.ru",
"password": "qwerty",
"familyName": "Иванов",
"name": "Иван",
"middleName": null,
"role": "a78b9730-dea9-4a2c-a97c-011424117d1b"
}
```

 - Редактирование пользователя<br>
Запрос: PUT /users/<:id><br>
Метод запроса: PUT<br>
URL сервиса: /users/<:id> (где «<:id>» - уникальный идентификатор редактируемого пользователя)<br>
Query string параметры: отсутствуют<br>

Тело запроса: содержит объект формата JSON с параметрами:<br>
``` yaml
{
"email": "test@test.ru",
"familyName": "Сидоров",
"name": "Иван",
"middleName": null,
}
```

 - Изменение пароля пользователя<br>
Запрос: POST /users/<:id>/set-password<br>
Метод запроса: POST<br>
URL сервиса: /users/<:id>/set-password (где «<:id>» - уникальный идентификатор редактируемого пользователя)<br>
Query string параметры: отсутствуют<br>

Тело запроса: содержит объект формата JSON с параметрами:<br>
``` yaml
{
"password": "qwerty",
"confirmPassword": "qwerty"
}
```

 - Изменение роли пользователя<br>
Запрос: POST /users/<:id>/set-role<br>
Метод запроса: POST<br>
URL сервиса: /users/<:id>/set- role (где «<:id>» - уникальный идентификатор редактируемого пользователя)<br>
Query string параметры: отсутствуют<br>

Тело запроса: содержит объект формата JSON с параметрами:<br>
``` yaml
{
"role": "d31b9730-dea9-4a2c-a97c-011424117d1b"
}
```

 - Изменение статуса пользователя<br>
Запрос: POST /users/<:id>/<:state><br>
Метод запроса: POST<br>
URL сервиса: /users/<:id>/<:state> (где «<:id>» - уникальный идентификатор редактируемого пользователя, <:state> - новый статус пользователя active/blocked)<br>
Query string параметры: отсутствуют<br>
Тело запроса: отсутствует<br>

 - Удаление пользователя<br>
Запрос: DELETE /users/<:id><br>
Метод запроса: DELETE<br>
URL сервиса: /users/<:id> (где «<:id>» - уникальный идентификатор редактируемого пользователя)<br>
Query string параметры: отсутствуют<br>
Тело запроса: отсутствует<br>

 - Получить список пользователей<br>
Запрос: GET /users<br>
Метод запроса: GET<br>
URL сервиса: /users<br>
Query string параметры: отсутствуют<br>
Тело запроса: отсутствует<br>

 - Получить информацию о пользователе<br>
Запрос: GET /users/<:id><br>
Метод запроса: GET<br>
URL сервиса: /users/<:id> (где «<:id>» - уникальный идентификатор редактируемого пользователя)<br>
Query string параметры: отсутствуют<br>
Тело запроса: отсутствует<br>

 - Получить список ролей<br>
Запрос: GET /roles<br>
Метод запроса: GET<br>
URL сервиса: /roles<br>
Query string параметры: отсутствуют<br>
Тело запроса: отсутствует<br>
