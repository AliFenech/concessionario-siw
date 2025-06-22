CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1 INCREMENT 1;


-- Inserimento dell'utente (cliente)
--insert into utente (id, cognome, data_nascita, email, nome) values (nextval('hibernate_sequence'), 'Clementucci', '2002-09-18', 'jacopoclementucci@gmail.com', 'Jacopo');

-- Inserimento delle credenziali collegate all'utente appena creato
--insert into credentials (id_cliente, id_dipendente, id, password_encode, ruolo, username) values (currval('hibernate_sequence'), null, nextval('hibernate_sequence'), '$2a$10$gKGUATrqKdpK3pX8lTRcCeOHk4dSL1I5KPc.csNEjYibvxT1Yw5KK', 'CLIENT', 'jacoclem');

insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Roma', 'Via Rossi 55');
insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Milano', 'Via Bianchi 44');
insert into punto_vendita (id, citta, indirizzo) values  (nextval('hibernate_sequence'), 'Napoli', 'Via Verdi 33');