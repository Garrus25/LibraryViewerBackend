create table rate (
                         rate_id integer not null,
                         user_id integer not null,
                         rate_type varchar(32) not null,
                         rate_value integer not null,
                         primary key (rate_id, user_id, rate_type)

);