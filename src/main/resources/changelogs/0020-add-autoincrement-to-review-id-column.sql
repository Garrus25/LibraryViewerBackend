CREATE SEQUENCE reviews_review_id_seq;

ALTER TABLE reviews
    ALTER COLUMN id
        SET DEFAULT nextval('reviews_review_id_seq');

ALTER SEQUENCE reviews_review_id_seq OWNED BY reviews.id;