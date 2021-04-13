
DELETE FROM person;
DELETE FROM contact;
DELETE FROM address;
DELETE FROM person_address;
DELETE FROM t_user_role;
DELETE FROM t_user;
DELETE FROM role;



INSERT INTO person(id, name, email) VALUES
(1, 'Victor', 'victor@gmail.com'),
(2, 'Oleg', 'oleg@gmail.com'),
(3, 'Stas', 'stas@gmail.com');

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

INSERT INTO t_user(id, username, password) VALUES
(1, 'admin', '$2y$10$amKWsumHOqedM91VmGfoTOmXiDMGsSag1wz5RVqn1ZwouRjz8M9v.'),
(2, 'user', '$2y$10$rdvDVFsXxcM6FkXuzF25BuiE25nsjX6EkWcSjnprRMmgGcxXiPIvW'),
(3, 'editor', '$2y$10$M0snJuLfztqcB.5dTXl3Geq8NCKu/06tdsZmB6XRVWBAuvsdjCVti');

INSERT INTO role(id, role_name) VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'EDITOR');

INSERT INTO t_user_role(user_fk, role_fk) VALUES
(1,1),
(2,2),
(3,3);



