-- create table genders
CREATE TABLE genders(
 id bigint NOT NULL DEFAULT nextval('genders_id_seq'::regclass),
 name VARCHAR(255) NOT NULL,
 created_at timestamp without time zone DEFAULT NOW(), -- timestamp
 updated_at timestamp without time zone DEFAULT NOW(), -- timestamp
 CONSTRAINT genders_id_pkey PRIMARY KEY (id)
);

 -- init genders sequence
ALTER SEQUENCE genders_id_seq OWNED BY genders.id;

-- create genders timestamp function
CREATE OR REPLACE FUNCTION trigger_set_timestamp_on_genders()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- create genders timestamp trigger
CREATE TRIGGER set_timestamp_on_genders
BEFORE UPDATE ON genders
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp_on_genders();
