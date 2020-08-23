-- create table jobs
CREATE TABLE jobs(
 id bigint NOT NULL DEFAULT nextval('jobs_id_seq'::regclass),
 name varchar(255) NOT NULL,
 salary NUMERIC(9,2) NOT NULL,
 created_at timestamp without time zone DEFAULT NOW(), -- timestamp
 updated_at timestamp without time zone DEFAULT NOW(), -- timestamp
 CONSTRAINT jobs_id_pkey PRIMARY KEY (id)
);

 -- init jobs sequence
ALTER SEQUENCE jobs_id_seq OWNED BY jobs.id;

-- create jobs timestamp function
CREATE OR REPLACE FUNCTION trigger_set_timestamp_on_jobs()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- create jobs timestamp trigger
CREATE TRIGGER set_timestamp_on_jobs
BEFORE UPDATE ON jobs
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp_on_jobs();
