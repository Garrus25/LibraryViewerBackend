create table users (
    id int8 not null,
    email varchar(64),
    password varchar(32),
    username varchar(32),
    primary key (id)
);