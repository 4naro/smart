drop table if exists booking;
drop table if exists person;
drop table if exists place;

create table person (
    person_id serial primary key, 
    email varchar (30)
);

begin;
        insert into person (email) values ('mariorossi@gmail.com');
        insert into person (email) values ('ginaverdi@live.it');
commit;

create table place (
    place_id serial primary key, 
    name varchar (30)
);

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