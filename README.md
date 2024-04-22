<span style="font-size:48px;">***ShareIt - микросервисное приложение для аренды вещей:***</span>

 <span style="font-size:48px;">***ShareIt - Стек:***</span>
- Java
- Spring Boot
- Spring JPA
- PostgreSQL
- REST API
- Docker
- Mockito, JUnit
- Lombok

  ![shareit](https://github.com/Kirillzhukov737/java-shareit/assets/110101893/a064f862-552f-4fc3-bc1c-e40a6c992507)


<span style="font-size:48px;">***ShareIt - О проекте***</span>

Сервис, который позволяет пользователям рассказывать, какими вещами они готовы поделиться, а также находить нужную вещь и брать её в аренду на какое-то время


<span style="font-size:48px;">***ShareIt - Приложение содержит микросервисы:***</span>

- Gateway для валидации запросов,
- Server, содержащий бизнес-логику
  
***Каждый микросервис запускается в собственном Docker контейнере.***

<span style="font-size:48px;">***Основная функциональность:***</span>

- Регистрация, обновление и получение пользователей
- Добавление, обновление, получение, а также поиск по предметам
- Управление заявками на аренду вещей
- Обработка запросов на аренду желаемых вещей
- Комментирование успешно завершённой аренды

  
<span style="font-size:48px;">***Эндпоинты:***</span>

- POST /users - добавление пользователя
- PATCH /users/{userId} - обновление данных пользователя
- GET /users/{userId} - получение данных пользователя
- GET /users/ - получение списка пользователей



- POST /items - добавление вещи
- PATCH /items/{itemId} - обновление данных вещи
- GET /items/{itemId} - получение данных вещи
- GET /items/ - получение списка вещей
- GET /items/search - поиск вещей по тексту в параметре text
- POST /items/{itemId}/comment - добавление отзыва к вещи после завершенного бронирования



- POST /requests - добавление запроса на бронирование
- GET /requests/{requestId} - получение бронирования
- GET /items/all - получение списка бронирований
- GET /items - получение списка бронирований по id пользователя в заголовке запроса



- PATCH /bookings/{bookingId} - обновление данных бронирования
- PATCH /bookings/{bookingId} - одобрение или отклонение бронирования по параметру approved
- GET /bookings/{bookingId} - получение данных о бронировании
- GET /bookings/ - получение бронирований по фильтрам state, from, size
- GET /bookings/owner - получение бронирований пользователя по фильтрам state, from, size
