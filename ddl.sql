CREATE DATABASE webfluxpoc;

USE webfluxpoc;

CREATE TABLE TB_CUSTOMER(
    id BIGINT not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    PRIMARY KEY(id)
)