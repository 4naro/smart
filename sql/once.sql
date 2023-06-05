
drop schema if exists smart cascade;
drop user if exists smart;

create user smart with password 'password';
create schema authorization smart;
