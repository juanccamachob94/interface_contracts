-- create table employees
CREATE TABLE employees(
 id bigint NOT NULL DEFAULT nextval('employees_id_seq'::regclass),
 gender_id BIGINT NOT NULL,
 job_id BIGINT NOT NULL,
 name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 birthdate DATE,
 CONSTRAINT employees_id_pkey PRIMARY KEY (id)
);

-- foreign contraint
ALTER TABLE employees
ADD CONSTRAINT fk_employees_genders
FOREIGN KEY (gender_id) REFERENCES genders (id);

-- foreign contraint
ALTER TABLE employees
ADD CONSTRAINT fk_employees_jobs FOREIGN KEY (job_id) REFERENCES jobs (id);

 -- init employees sequence
ALTER SEQUENCE employees_id_seq OWNED BY employees.id;
