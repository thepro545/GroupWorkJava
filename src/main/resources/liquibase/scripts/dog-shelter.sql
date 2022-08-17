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
    chat_id       integer             not null,
    dog_id        integer             not null,
    cat_id        integer             not null,
    status        varchar(30)         not null,
    FOREIGN KEY (id) REFERENCES reportData (person_id)
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

create table cat
(
    id            integer PRIMARY KEY not null,
    breed         varchar             not null,
    "name"        varchar             not null,
    year_of_birth integer             not null,
    description   varchar,
    FOREIGN KEY (id) REFERENCES person (cat_id)
);

create table reportData
(
    id          integer PRIMARY KEY not null,
    chat_id     integer             not null,
    ration      varchar             not null,
    health      varchar             not null,
    habits      varchar             not null,
    filePath    varchar             not null,
    days        integer             not null,
    person_id   integer             not null,
    caption     varchar             not null,
    lastMessage date                not null,
    data        bytea               not null
);

