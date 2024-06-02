create table comments (
                       id SERIAL not null,
                       body text,
                       username varchar(32),
                       user_id varchar(32),
                       parent_id bigint,
                       created_at date,
                       primary key (id)
);