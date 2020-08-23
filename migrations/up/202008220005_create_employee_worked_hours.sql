-- create table employee_worked_hours
CREATE TABLE employee_worked_hours(
 id bigint NOT NULL DEFAULT nextval('employee_worked_hours_id_seq'::regclass),
 employee_id BIGINT NOT NULL,
 WORKED_HOURS SMALLINT NOT NULL,
 WORKED_DATE DATE NOT NULL,
 CONSTRAINT employee_worked_hours_id_pkey PRIMARY KEY (id)
);

-- foreign contraint
ALTER TABLE employee_worked_hours
ADD CONSTRAINT fk_employee_worked_hours_employees
FOREIGN KEY (employee_id) REFERENCES jobs (id);

 -- init employee_worked_hours sequence
ALTER SEQUENCE employee_worked_hours_id_seq OWNED BY employee_worked_hours.id;
