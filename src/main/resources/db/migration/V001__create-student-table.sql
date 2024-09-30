create table student (
    id bigserial primary key not null,
    active boolean default true not null,
    register_date date not null,
    cpf varchar(11) unique not null,
    name varchar(60) not null,
    age int not null
);