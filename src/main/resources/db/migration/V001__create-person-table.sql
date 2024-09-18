create table person (
    id bigserial not null ,
    cpf varchar(11) not null ,
    name varchar(60) not null ,
    telephone varchar(20) not null ,
    address varchar(255) not null ,

    primary key (id)
);

alter table person
add constraint uk_cpf unique (cpf);
