-- liquibase formatted sql

-- changeset maxon4ik:1
create table person
(
    id            integer PRIMARY KEY not null,
    "name"        varchar(30)         not null,
    year_of_birth integer             not null,
    phone         varchar             not null,
    mail          varchar             not null,
    address       varchar             not null,
    chat_id       integer             not null
);

create table dog
(
    id            integer PRIMARY KEY not null,
    breed         varchar             not null,
    "name"        varchar             not null,
    year_of_birth integer             not null,
    description   varchar,
    FOREIGN KEY (id) REFERENCES person (dog_id)
);


