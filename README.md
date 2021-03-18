# �������� �����

* ���������� �������� ����� - �������� ���������� � ��������: ��� ������, ���, email � ������ ���������.
* ������ � ��������� �������� � ���� ������.
* ���������� ������� � ������������� (���������� ����� �������������, ��������� � �������� ���������� � ������������) ������������ ����������� REST WebService. 
* Spring Security ��� ���������� ������ �������� �����.
* ������������ ����������: SpringBoot, SpringMVC, SpringData, Spring Security, Swagger, Map Struct, Lombok, Hibernate, Heroku, PostgreSQL. ��� ������ ������� ������������� Maven.

## ������������ ����������

������� �������� � ���������� ������ ��������� � ����� resources

### ���������� �������

������������ ������ Spring Security ��������� �������������� ���������� �������. ����� ���� 3 ����: ADMIN, USER, EDITOR.
ADMIN ����� �������� ���� �������������, ������� �������������, ������ ����� �������� � ����������.
USER ����� ������������� � ��������� ��������.
EDITOR = USER + �������� ��������.

���������� ������� � ������������� � ��������� ���������� ����������� HTTP �������� �� localhost ����� ���� 8081.

������ ���� ���������:

GET ������ �� http://localhost:8081/address-book/persons

���������� � �������� �� id:

GET ������ �� http://localhost:8081/address-book/persons/{id}

���������� � �������� �� email:

GET ������ �� http://localhost:8081/address-book/persons/?email=emailToFind

�������� ������ ����� ������:

POST ������ �� http://localhost:8081/address-book/persons

���� �������:

JSON � ����������� name, contacts � addresses:
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

��������� ����� ������:

PUT ������ �� http://localhost:8081/address-book/persons

���� �������:

����� �� ��� � ��� �������� + ���� id:.

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

�������� ���������� � �������� �� id:

DELETE ������ �� http://localhost:8081/address-book/persons/{id}

�������� ������ ������������:

POST ������ �� http://localhost:8081/address-book/users

���� �������:

JSON � ����������� username � password:
```
{
    "username": "username",
    "username": "password"
}
```

���������� � ����������, ��������� ������� ��������� � �������������� (users ������ persons)



