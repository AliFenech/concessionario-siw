-- Crea la sequence usata da Hibernate, se non esiste
CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1 INCREMENT 1;

-- Popola i Punti Vendita con ID fissi
insert into punto_vendita (id, citta, indirizzo) values (1, 'Roma', 'Via Rossi 55');
insert into punto_vendita (id, citta, indirizzo) values (2, 'Milano', 'Via Bianchi 44');
insert into punto_vendita (id, citta, indirizzo) values (3, 'Napoli', 'Via Verdi 33');

-- Allinea la sequence per evitare conflitti sui futuri ID
SELECT setval('hibernate_sequence', 3, true);

-- Account Admin
insert into utente(data_nascita,id,cognome,email,nome)
VALUES ('2000-11-11', 1, 'admin', 'admin@gmail.com', 'admin');

insert into credentials(cliente_id,dipendente_id,id,password_encode,ruolo,username)
VALUES (null, 1, 1, '$2a$10$aAvomhOB.EGPdDyDJsJV4uMElQrtDFdjE4j5S3GcqE0TVULNYD/FS', 'ADMIN', 'admin');

-- Account Cliente
insert into utente(data_nascita,id,cognome,email,nome)
VALUES ('2025-05-26', 2, 'cliente', 'cliente@gmail.com', 'cliente');

insert into credentials(cliente_id,dipendente_id,id,password_encode,ruolo,username)
VALUES (2, null, 2, '$2a$10$j0D3ihWhsI/BTGzwI9k1tOyspT1ev4Ump.X.TQLA16lJl0cQm4VrW', 'CLIENTE', 'cliente');