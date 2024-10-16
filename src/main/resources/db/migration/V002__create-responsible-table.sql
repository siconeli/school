create table responsible (
    id bigserial primary key not null ,
    active boolean default true not null,
    register_date date not null,
    student_id bigint not null,
    cpf varchar(11) unique not null ,
    name varchar(60) not null ,
    telephone varchar(20) not null ,
    address varchar(255) not null ,
    profession varchar(60),
    authorized boolean default true not null,
    kinship varchar(20) not null
);

alter table responsible
    add constraint fk_student
        foreign key (student_id) references student (id);
