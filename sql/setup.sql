drop table if exists type_everybody;
drop table if exists everybody;
drop table if exists booking;
drop table if exists place;
drop table if exists user_;

create table type_everybody (
type_id serial primary key,
type varchar -- U & P
);

insert into type_everybody (type) values ('User');
insert into type_everybody (type) values ('Place');


create table everybody (
    everybody_id serial primary key,      
    email varchar (30) UNIQUE NOT NULL,
    password varchar (30) NOT NULL,
    type_id integer,

    foreign key (type_id) references type_everybody (type_id)
);

insert into everybody (email, password) values ('andreachiarello@gmail.com', 'password');
insert into everybody (email, password) values ('silviatarsitano@gmail.com', 'password');


create table user_ (
    user_id serial primary key, 
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    everybody_id integer unique,

    foreign key (everybody_id) references everybody (everybody_id)
    );

-- RIVEDERE
insert into user_ (first_name, last_name) values ('Claudia', 'Fornaro');
insert into user_ (first_name, last_name) values ('Alessandra', 'Tutino');
--insert into user_ (email, password) values ('andreachiarello@gmail.com', 'password');
--insert into user_ (email, password) values ('fabrizialorusso@gmail.com', 'password');
--insert into user_ (email, password) values ('silviatarsitano@gmail.com', 'password');

create table place (
    place_id serial primary key, 
    name varchar (50) NOT NULL,
    address varchar (50) NOT NULL,
    phone varchar (20),
    people integer, 
    everybody_id integer unique,

     foreign key (everybody_id) references everybody (everybody_id)
);
-- RIVEDERE
insert into place (name, address, phone, people, everybody_id) values ('Roxy Bar', 'xxx', '1234567890', 20);
insert into place (name, address, phone, people, everybody_id) values ('Bar Mario', 'yyy', '1234567890', 20);
insert into place (name, address, phone, people, everybody_id) values ('Area Paperino', 'zzz', '1234567890', 20);
insert into place (name, address, phone, people, everybody_id) values ('Community L’isola che non c’è', 'www', '1234567890', 20);
insert into place (name, address, phone, people, everybody_id) values ('Area 51', 'jjj', '1234567890', 20);

create table booking (
    booking_id serial primary key,
    user_id integer,
    place_id integer,
    res_in timestamp,
    res_out timestamp,
    
    unique (user_id, place_id, res_in),
    foreign key (user_id) references user_ (user_id),
    foreign key (place_id) references place (place_id)
);

insert into booking (user_id, place_id, res_in, res_out) values (1, 1, '2023-07-01 08:00', '2023-07-01 12:00');
insert into booking (user_id, place_id, res_in, res_out) values (2, 2, '2023-07-01 09:00', '2023-07-01 16:00'); 
--insert into booking (user_id, place_id, res_in, res_out) values (3, 3, '2023-07-01 10:00', '2023-07-01 18:00');
--insert into booking (user_id, place_id, res_in, res_out) values (4, 4, '2023-07-01 09:00', '2023-07-01 13:00');
--insert into booking (user_id, place_id, res_in, res_out) values (5, 5, '2023-07-01 15:00', '2023-07-01 18:00');








