CREATE TABLE person (
    id BIGSERIAL NOT NULL,
    cpf varchar(11) NOT NULL,
    name VARCHAR(60) NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE person
ADD CONSTRAINT uk_cpf UNIQUE(cpf);
