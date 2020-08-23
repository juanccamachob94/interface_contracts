-- create table employee_worked_hours
CREATE TABLE employee_worked_hours(
 id bigint NOT NULL DEFAULT nextval('employee_worked_hours_id_seq'::regclass),
 employee_id BIGINT NOT NULL,
 WORKED_HOURS SMALLINT NOT NULL,
 WORKED_DATE DATE NOT NULL,
 created_at timestamp without time zone DEFAULT NOW(), -- timestamp
 updated_at timestamp without time zone DEFAULT NOW(), -- timestamp
 CONSTRAINT employee_worked_hours_id_pkey PRIMARY KEY (id)
);

-- foreign contraint
ALTER TABLE employee_worked_hours
ADD CONSTRAINT fk_employee_worked_hours_employees
FOREIGN KEY (employee_id) REFERENCES jobs (id);

 -- init employee_worked_hours sequence
ALTER SEQUENCE employee_worked_hours_id_seq OWNED BY employee_worked_hours.id;

-- create employee_worked_hours timestamp function
CREATE OR REPLACE FUNCTION trigger_set_timestamp_on_employee_worked_hours()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- create employee_worked_hours timestamp trigger
CREATE TRIGGER set_timestamp_on_employee_worked_hours
BEFORE UPDATE ON employee_worked_hours
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp_on_employee_worked_hours();
