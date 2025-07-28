-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into userobserver (nome, email, matricula, password, role, tipo) values
('Administrador', 'mayara.dnascimentos@gmail.com', '123456', '123456', 'ADMIN', 'SERVIDOR');

insert into botao (local) values ('Sala de Reunião'), ('Recepção'),
('Laboratório de Programação 1'), ('Laboratório de Programação 2'),
('Laboratório de Programação 3'), ('Laboratório de Programação 4') ;