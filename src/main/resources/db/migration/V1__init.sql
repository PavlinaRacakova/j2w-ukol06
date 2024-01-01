CREATE TABLE BUSINESS_CARD
(
    id         IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    company      VARCHAR(100) NOT NULL,
    street      VARCHAR(100) NOT NULL,
    town       VARCHAR(100) NOT NULL,
    zip_code        CHAR(5)      NOT NULL,
    email      VARCHAR(100),
    phone_number    VARCHAR(20),
    web        VARCHAR(100)
);


INSERT INTO BUSINESS_CARD (name, company, street, town, zip_code, email, phone_number, web)
VALUES ('Dita (Přikrylová) Formánková', 'Czechitas z. s.', 'Václavské náměstí 837/11', 'Praha 1', '11000', 'dita@czechitas.cs', '+420 800123456',
        'www.czechitas.cz'),
       ('Barbora Bühnová', 'Czechitas z. s.', 'Václavské náměstí 837/11', 'Praha 1', '11000', NULL, '+420 800123456', 'www.czechitas.cz'),
       ('Monika Ptáčníková', 'Czechitas z. s.', 'Václavské náměstí 837/11', 'Praha 1', '11000', 'monika@czechitas.cs', '+420 800123456', 'www.czechitas.cz'),
       ('Mirka Zatloukalová', 'Czechitas z. s.', 'Václavské náměstí 837/11', 'Praha 1', '11000', 'mirka@czechitas.cs', NULL, 'www.czechitas.cz');
