create table responsible (
    id bigserial not null ,
    cpf varchar(11) not null ,
    name varchar(60) not null ,
    telephone varchar(20) not null ,
    address varchar(255) not null ,
    authorized boolean default true,
    kinship varchar(60) not null,

    primary key (id)
);

alter table responsible
    add constraint uk_cpf unique (cpf);