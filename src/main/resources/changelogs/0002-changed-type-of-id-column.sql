CREATE SEQUENCE serial AS integer START 1 OWNED BY users.id;
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('serial');