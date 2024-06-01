CREATE SEQUENCE authors_author_id_seq;

ALTER TABLE authors
    ALTER COLUMN author_id
        SET DEFAULT nextval('authors_author_id_seq');

ALTER SEQUENCE authors_author_id_seq OWNED BY authors.author_id;