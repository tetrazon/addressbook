# јдресна€ книга

* ѕриложение адресна€ книга - хранение информации о контакте: его адреса, им€, email и номера телефонов.
* ƒанные о контактах хран€тс€ в базе данных.
* ”правление данными о пользовател€х (добавление новых пользователей, изменение и удаление информации о пользователе) производитс€ посредством REST WebService. 
* Spring Security дл€ управлени€ данных адресной книги.
* »спользуемые технологии: SpringBoot, SpringMVC, SpringData, Spring Security, Swagger, Map Struct, Lombok, Hibernate, Heroku, PostgreSQL. ƒл€ сборки проекта использовалс€ Maven.

##  онфигураци€ приложени€

—крипты создани€ и наполнени€ таблиц наход€тс€ в папке resources

### ”правление данными

ѕодключенный модуль Spring Security позвол€ет контролировать управление данными. ¬сего есть 3 роли: ADMIN, USER, EDITOR.
ADMIN может измен€ть роли пользователей, удал€ть пользователей, делать любые операции с контактами.
USER может просматривать и добавл€ть контакты.
EDITOR = USER + измен€ть контакты.

”правление данными о пользовател€х и контактах происходит посредством HTTP запросов на localhost через порт 8081.

—писок всех контактов:

GET запрос на http://localhost:8081/address-book/persons

»нформаци€ о контакте по id:

GET запрос на http://localhost:8081/address-book/persons/{id}

»нформаци€ о контакте по email:

GET запрос на http://localhost:8081/address-book/persons/?email=emailToFind

—оздание нового блока данных:

POST запрос на http://localhost:8081/address-book/persons

“ело запроса:

JSON с параметрами name, contacts и addresses:
```
{
    "name": "Person_name",
    "contacts": [
        {
            "telephone": "+375291111444"
        },
        {
            "telephone": "+375291111111"
        },
        {
            "telephone": "+375291111111"
        }
    ],
    "addresses": [
        {
            "city": "Minsk",
            "street": "Esenina"
        },
        {
            "city": "Minsk",
            "street": "Lenina"
        }
    ]
}
```

»зменение блока данных:

PUT запрос на http://localhost:8081/address-book/persons

“ело запроса:

такое же как и при создании + поле id:.

```
{
    "id": 13,
    "name": "Person_name",
    "contacts": [
        {
            "telephone": "+375291111555"
        }
    ],
    "addresses": [
        {
            "city": "Minsk",
            "street": "Esenina"
        }
    ]
}
```

”даление информации о контакте по id:

DELETE запрос на http://localhost:8081/address-book/persons/{id}

—оздание нового пользовател€:

POST запрос на http://localhost:8081/address-book/users

“ело запроса:

JSON с параметрами username и password:
```
{
    "username": "username",
    "username": "password"
}
```

јналогично с контактами, остальные запросы одинаковы с пользовател€ми (users вместо persons)



