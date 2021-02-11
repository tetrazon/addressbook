
DELETE FROM person;
DELETE FROM contact;
DELETE FROM address;
DELETE FROM person_address;


INSERT INTO person(id, name) VALUES
(1, 'Victor'),
(2, 'Oleg'),
(3, 'Stas');

INSERT INTO contact(id, telephone, person_fk) VALUES
(1, '+375297777777', 1),
(2, '+375297777778', 1),
(3, '+375297777779', 1),
(4, '+375297777770', 2),
(5, '+375297777771', 3);

INSERT INTO address(id, city, street) VALUES
(1, 'Minsk', 'Lenina'),
(2, 'Minsk', 'Esenina'),
(3, 'Minsk', 'Beletskogo');

INSERT INTO person_address(person_fk, address_fk) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

