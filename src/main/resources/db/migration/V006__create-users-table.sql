create table users(
    id bigserial primary key unique not null,
    login text unique not null,
    password text not null,
    role text not null
);