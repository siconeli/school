create table contract (
    id bigserial primary key not null,
    student_id bigint not null,
    period varchar(20) not null,
    date_input date not null,
    date_output date not null
);

alter table contract
    add constraint fk_student
        foreign key (student_id) references student (id);