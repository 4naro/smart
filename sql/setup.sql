drop table if exists booking;
drop table if exists person;
drop table if exists place;

create table person (
    person_id serial primary key, 
    email varchar (30),
    password varchar (30)
    );

insert into person (email, password) values ('Claudia Fornaro', 'password');
insert into person (email, password) values ('Alessandra Tutino', 'password');
insert into person (email, password) values ('Andrea Chiarello', 'password');
insert into person (email, password) values ('Fabrizia Lo Russo', 'password');
insert into person (email, password) values ('Silvia Tarsitano', 'password');


-- PROVE 
-- begin;
       -- insert into person (email) values ('mariorossi@gmail.com');
       -- insert into person (email) values ('ginaverdi@live.it');
       -- delete from person where person_id > 2; -- cancellazione email duplicate
-- commit;

create table place (
    place_id serial primary key, 
    email varchar (30),
    password varchar (30),
    name varchar (50),
    address varchar (50),
    phone integer
);

insert into place (email, password, name, address, phone) values ('roxybar@gmail.com','password', 'Roxy Bar', 'xxx', 1234567890);
insert into place (email, password, name, address, phone) values ('barmario@gmail.com', 'password', 'Bar Mario', 'yyy', 1234567890);
insert into place (email, password, name, address, phone) values ('areapaperino@gmail.com', 'password', 'Area Paperino', 'zzz', 1234567890);
insert into place (email, password, name, address, phone) values ('isolachenonce@gmail.com', 'password', 'Community L’isola che non c’è', 'www', 1234567890);
insert into place (email, password, name, address, phone) values ('area51@gmail.com', 'password', 'Area 51', 'jjj', 1234567890);


begin;
        insert into place (name) values ('Roxy Bar');
        insert into place (name) values ('Bar Mario');
commit;

create table booking (
    person_id integer,
    place_id integer,
    reservation date,

    primary key (person_id, place_id, reservation),
    foreign key (person_id) references person (person_id),
    foreign key (place_id) references place (place_id)
);

insert into booking (person_id, place_id, reservation) values (1, 1, current_date); 
insert into booking (person_id, place_id, reservation) values (2, 2, current_date);