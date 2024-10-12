create table users(
    id bigserial primary key unique not null,
    login varchar(20) unique not null,
    password varchar(20) not null
);