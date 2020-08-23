-- create table jobs
CREATE TABLE jobs(
 id bigint NOT NULL DEFAULT nextval('jobs_id_seq'::regclass),
 name varchar(255) NOT NULL,
 salary NUMERIC(9,2) NOT NULL,
 CONSTRAINT jobs_id_pkey PRIMARY KEY (id)
);

 -- init jobs sequence
ALTER SEQUENCE jobs_id_seq OWNED BY jobs.id;
