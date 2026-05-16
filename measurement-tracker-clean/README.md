# measurement-tracker-clean

Clean Spring Boot учебный проект для учёта показаний счётчиков с JWT, OCR-очередью (FIFO), PostgreSQL, Swagger и Thymeleaf.

## Стек
Java 21, Spring Boot, Web, Data JPA, Validation, Security, PostgreSQL, Maven, Swagger, Thymeleaf.

## Запуск
1. Создать БД `measurement_tracker` в PostgreSQL.
2. Проверить `src/main/resources/application.properties`.
3. Установить Tesseract и указать путь `app.ocr.tesseract-path`.
4. Запустить: `mvn spring-boot:run`.

## OCR очередь
`POST /ocr/tasks` только сохраняет файл и создаёт `OcrTask(NEW)`. `OcrTaskProcessor` по расписанию берёт задачи FIFO, делает preprocess + Tesseract, создаёт `MeterReading`, ставит DONE/FAILED.

## Swagger
`http://localhost:8080/swagger-ui.html`

## Frontend
Страницы в `src/main/resources/templates`.

## Ограничения
Демо-frontend минималистичен; можно улучшить JS-интеграцией и UI.
