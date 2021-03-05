DROP TABLE IF EXISTS contact CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS person_address CASCADE;

DROP SEQUENCE IF EXISTS address_seq;
DROP SEQUENCE IF EXISTS contact_seq;
DROP SEQUENCE IF EXISTS person_seq;

CREATE SEQUENCE address_seq START WITH 10;
CREATE SEQUENCE contact_seq START WITH 10;
CREATE SEQUENCE person_seq START WITH 10;

CREATE TABLE address
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('address_seq'),
    city       VARCHAR NOT NULL,
    street     VARCHAR NOT NULL,
    UNIQUE (city, street)
);

CREATE TABLE person
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('person_seq'),
    name     VARCHAR NOT NULL,
    email    VARCHAR NOT NULL UNIQUE
);

CREATE TABLE contact
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('contact_seq'),
    telephone   VARCHAR NOT NULL,
    person_fk   INTEGER NOT NULL,
    FOREIGN KEY (person_fk) REFERENCES person(id) ON DELETE CASCADE
);

CREATE TABLE person_address (
  person_fk   INTEGER NOT NULL,
  address_fk   INTEGER NOT NULL,
  PRIMARY KEY (person_fk, address_fk),
  FOREIGN KEY (person_fk) REFERENCES person(id) ON DELETE CASCADE,
  FOREIGN KEY (address_fk) REFERENCES address(id) ON DELETE CASCADE

);
