insert into room_types(capacity, cost, label) values (1, 1, 'generic s');
insert into room_types(capacity, cost, label) values (2, 3, 'generic m');
insert into room_types(capacity, cost, label) values (3, 4, 'generic l');
insert into room_types(capacity, cost, label) values (1, 4, 'room with grass');
insert into rooms(type) values (1);
insert into rooms(type) values (1);
insert into rooms(type) values (1);
insert into rooms(type) values (2);
insert into rooms(type) values (3);
insert into rooms(type) values (4);

insert into furniture_types(cost, name) values (1, 'bed');
insert into furniture(state, type, room) values (1, 1, 1);

insert into species(name, description) values ('wolf', 'forest predator');
insert into species(name, description) values ('rabbit', 'cutie');

insert into room_compatible(specie, room_type) values (1, 1);
insert into room_compatible(specie, room_type) values (1, 2);
insert into room_compatible(specie, room_type) values (1, 3);
insert into room_compatible(specie, room_type) values (2, 4);

insert into users(email, firstname, lastname, pass, role, specie) values ('user@mail.ru', 'User', 'Userov', 'open_pass', 'USER', 'None');

insert into book_records(arrival, departure, room, uid) values ('2023-11-11', '2023-11-15', 1, 1);
insert into book_records(arrival, departure, room, uid) values ('2023-11-24', '2023-11-28', 1, 1);
insert into book_records(arrival, departure, room, uid) values ('2023-11-14', '2023-11-25', 3, 1);
insert into book_records(arrival, departure, room, uid) values ('2023-11-13', '2023-11-17', 5, 1);
