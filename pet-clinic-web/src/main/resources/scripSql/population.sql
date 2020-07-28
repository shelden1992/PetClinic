
INSERT INTO type(name) VALUES ( 'DOG');
INSERT INTO type(name) VALUES ( 'CAT');
INSERT INTO type(name) VALUES ( 'PARROT');
INSERT INTO type(name) VALUES ( 'HAMSTER');
INSERT INTO type(name) VALUES ( 'RABBIT');
INSERT INTO speciality(id, description) value (1,'Surgeon');
INSERT INTO speciality(id, description) value (2,'Dog doctor');
INSERT INTO owner(id, email, first_name, last_name, password, address, telephone) values (1,'shelupets1992@gmail.com', 'Den', 'Shelupets', '$2a$10$sp.2NkfxC0sFa/FPKg8WHOyGhho25O9b5PPZlmDd4cTYYdN3xDQd6','Kyiv', '+38050333333');
INSERT INTO owner(id, email, first_name, last_name, password, address, telephone) values (2,'shelden@gmail.com', 'Valera', 'Karat', '$2a$10$sp.2NkfxC0sFa/FPKg8WHOyGhho25O9b5PPZlmDd4cTYYdN3xDQd6','Kyiv', '+38066311111');
INSERT INTO pet(id, birth_date, name, owner_id, type_name) VALUES (1, '2020-07-22', 'Pudel', 1,'DOG');
INSERT INTO pet(id, birth_date, name, owner_id, type_name) VALUES (2, '2020-07-22', 'May', 2,'CAT');
INSERT INTO vet(id, email, first_name, last_name, password) VALUES (1,'admin@gmail.com', 'Vet', 'Vets','$2a$10$sp.2NkfxC0sFa/FPKg8WHOyGhho25O9b5PPZlmDd4cTYYdN3xDQd6');
INSERT INTO vet_specialties(vet_id, speciality_id) VALUES (1,2);
INSERT INTO visit(id, date_visit, description, pet_id, vet_id) VALUES (1, '2020-07-22','Профилактика глистов', 1,1);
INSERT INTO visit(id, date_visit, description, pet_id, vet_id) VALUES (2, '2020-07-22','Профилактика блох', 1,1);
INSERT INTO owner_pets(owner_id, pets_id) VALUES (1,1);
