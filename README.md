# Book Statistics Application

## Описание проекта

Book Statistics Application — это REST API для управления статистикой чтения книг. Приложение позволяет пользователям добавлять книги, отслеживать прогресс чтения и получать информацию о своих книгах. Аутентификация и авторизация реализованы через OAuth2 с использованием Google в качестве провайдера.

## Основные функции

- **Аутентификация через Google OAuth2**: Пользователи могут войти в систему с помощью своих Google аккаунтов.
- **Управление книгами**: Добавление, просмотр и управление книгами.
- **Отслеживание прогресса чтения**: Пользователи могут добавлять информацию о прогрессе чтения для каждой книги.
- **Swagger документация**: API документировано с использованием Swagger, что позволяет легко тестировать и использовать API.

## Технологии

- **Spring Boot**: Основной фреймворк для создания приложения.
- **Spring Security**: Для аутентификации и авторизации.
- **OAuth2**: Для интеграции с Google OAuth2.
- **JWT (JSON Web Tokens)**: Для управления сессиями пользователей.
- **PostgreSQL**: В качестве базы данных.
- **Swagger**: Для документации API.
- **Lombok**: Для сокращения boilerplate кода.
- **Log4j**: Для логирования.

## Установка и запуск

### Предварительные требования

- Java 21
- Maven
- Docker (опционально, для запуска PostgreSQL в контейнере)
- Google OAuth2 клиент (необходимо зарегистрировать приложение в Google Cloud Console и получить `client_id` и `client_secret`)

### Шаги для запуска

1. **Клонирование репозитория**

   ```bash
   git clone https://github.com/your-repo/book-statistics.git
   cd book-statistics

### Настройка переменных окружения

Создайте файл .env в корне проекта и добавьте следующие переменные:

```
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret
```

## Запуск PostgreSQL

### Вы можете использовать Docker для запуска PostgreSQL:

```bash
   docker-compose up -d
```

Или установите PostgreSQL локально и настройте подключение в application.yml.

## Сборка и запуск приложения

```bash
mvn clean install
mvn spring-boot:run
```
```
Приложение будет доступно по адресу: http://localhost:8080
```

## Доступ к Swagger UI

## После запуска приложения, откройте браузер и перейдите по адресу:

```
http://localhost:8080/swagger-ui.html
```

Здесь вы найдете документацию по всем доступным API и сможете протестировать их.

## Использование API
### Аутентификация
Авторизация через Google
```
Перейдите по адресу: http://localhost:8080/api/oauth/login
```
Вы будете перенаправлены на страницу авторизации Google.

После успешной авторизации вы получите JWT токен.

Использование токена

Добавьте полученный токен в заголовок запроса:

```
Authorization: Bearer <your-jwt-token>
```
## Примеры запросов

### Добавление книги
Запрос:

```
POST /api/books
Content-Type: application/json
Authorization: Bearer <your-jwt-token>
```
```
{
"title": "Example Book",
"author": "John Doe",
"totalPages": 300,
"coverImage": "http://example.com/cover.jpg"
}
```
Ответ:
```
{
"id": 1,
"title": "Example Book",
"author": "John Doe",
"totalPages": 300,
"coverImage": "http://example.com/cover.jpg"
}
```
### Получение списка книг
Запрос:

```
GET /api/books
Authorization: Bearer <your-jwt-token>
```
Ответ:

```
[
{
"id": 1,
"title": "Example Book",
"author": "John Doe",
"totalPages": 300,
"coverImage": "http://example.com/cover.jpg"
}
]
```
### Добавление прогресса чтения
Запрос:
```
POST /api/progress
Content-Type: application/json
Authorization: Bearer <your-jwt-token>
```
```
{
"date": "2023-10-12",
"pagesRead": 50,
"book": {
"id": 1
}
}
```
Ответ:
```
{
"id": 1,
"date": "2023-10-12",
"pagesRead": 50,
"book": {
"id": 1,
"title": "Example Book",
"author": "John Doe",
"totalPages": 300,
"coverImage": "http://example.com/cover.jpg"
}
}
```
## Логирование
Логирование настроено с использованием Log4j. Логи выводятся в консоль и содержат информацию о времени, уровне логирования, классе и сообщении.

Пример лога:
```
2023-10-12 14:30:45 INFO  BookController:45 - Добавлена новая книга: Example Book
```
## Тестирование
Для тестирования API можно использовать Swagger UI или инструменты вроде Postman.