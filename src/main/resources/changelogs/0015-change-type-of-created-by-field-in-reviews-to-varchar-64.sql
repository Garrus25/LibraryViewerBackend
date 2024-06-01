create table reviews (
                       id varchar(64) not null,
                       title varchar(64),
                       content text,
                       created_by varchar(32),
                       book_id varchar(64),
                       primary key (id)
);