-- create table genders
CREATE TABLE genders(
 id bigint NOT NULL DEFAULT nextval('genders_id_seq'::regclass),
 name VARCHAR(255) NOT NULL,
 CONSTRAINT genders_id_pkey PRIMARY KEY (id)
);

 -- init genders sequence
ALTER SEQUENCE genders_id_seq OWNED BY genders.id;
