create table books (
                       isbn varchar(64) not null,
                       author_id integer,
                       title text,
                       description text,
                       cover_name text,
                       publish_date date,
                       primary key (isbn)

);