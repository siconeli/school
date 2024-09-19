create table student (
     id bigserial not null,
     cpf varchar(11) not null,
     name varchar(60) not null,
     age int not null,

     primary key (id)
);

alter table student
    add constraint uk_cpfStudent unique(cpf);