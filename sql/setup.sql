drop table if exists booking;
drop table if exists place;
drop table if exists user_;


create table user_ (
    user_id serial primary key, 
    email varchar (30) UNIQUE NOT NULL,
    password varchar (30) NOT NULL
    );

insert into user_ (email, password) values ('Claudia Fornaro', 'password');
insert into user_ (email, password) values ('Alessandra Tutino', 'password');
insert into user_ (email, password) values ('Andrea Chiarello', 'password');
insert into user_ (email, password) values ('Fabrizia Lorusso', 'password');
insert into user_ (email, password) values ('Silvia Tarsitano', 'password');


-- PROVE 
-- begin;
       -- insert into user_ (email) values ('mariorossi@gmail.com');
       -- insert into user_ (email) values ('ginaverdi@live.it');
       -- delete from user_ where user_id > 2; -- cancellazione email duplicate
-- commit;

create table place (
    place_id serial primary key, 
    email varchar (30) UNIQUE NOT NULL,
    password varchar (30) NOT NULL,
    name varchar (50) NOT NULL,
    address varchar (50) NOT NULL,
    phone varchar (20) 
);

insert into place (email, password, name, address, phone) values ('roxybar@gmail.com','password', 'Roxy Bar', 'xxx', '1234567890');
insert into place (email, password, name, address, phone) values ('barmario@gmail.com', 'password', 'Bar Mario', 'yyy', '1234567890');
insert into place (email, password, name, address, phone) values ('areapaperino@gmail.com', 'password', 'Area Paperino', 'zzz', '1234567890');
insert into place (email, password, name, address, phone) values ('isolachenonce@gmail.com', 'password', 'Community L’isola che non c’è', 'www', '1234567890');
insert into place (email, password, name, address, phone) values ('area51@gmail.com', 'password', 'Area 51', 'jjj', '1234567890');


begin;
        insert into place (name) values ('Roxy Bar');
        insert into place (name) values ('Bar Mario');
commit;

create table booking (
    user_id integer,
    place_id integer,
    reservation date,

    primary key (user_id, place_id, reservation),
    foreign key (user_id) references user_ (user_id),
    foreign key (place_id) references place (place_id)
);

insert into booking (user_id, place_id, reservation) values (1, 1, current_date); 
insert into booking (user_id, place_id, reservation) values (2, 2, current_date);