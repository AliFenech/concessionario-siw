DROP SEQUENCE IF EXISTS hibernate_sequence CASCADE;
DROP SEQUENCE IF EXISTS credentials_sequence CASCADE;

CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;
CREATE SEQUENCE credentials_sequence START 1 INCREMENT 1;

insert into utente(id, data_nascita, cognome,email,nome)VALUES(nextval('hibernate_sequence'), '2000-11-11', 'admin', 'admin@gmail.com', 'admin');
insert into credentials(utente_id,id,password_encode,ruolo,username) values(currval('hibernate_sequence'),nextval('credentials_sequence'),'$2a$10$aAvomhOB.EGPdDyDJsJV4uMElQrtDFdjE4j5S3GcqE0TVULNYD/FS','ADMIN','admin');


insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Roma', 'Via Rossi 55');
insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Milano', 'Via Bianchi 44');
insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Napoli', 'Via Verdi 33');


