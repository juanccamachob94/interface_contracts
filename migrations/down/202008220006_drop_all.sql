DROP TRIGGER IF EXISTS set_timestamp_on_employee_worked_hours ON employee_worked_hours;
DROP TABLE IF EXISTS employee_worked_hours;
DROP SEQUENCE IF EXISTS employee_worked_hours_id_seq;

DROP TRIGGER IF EXISTS set_timestamp_on_employees ON employees;
DROP TABLE IF EXISTS employees;
DROP SEQUENCE IF EXISTS employees_id_seq;

DROP TRIGGER IF EXISTS set_timestamp_on_genders ON genders;
DROP TABLE IF EXISTS genders;
DROP SEQUENCE IF EXISTS genders_id_seq;

DROP TRIGGER IF EXISTS set_timestamp_on_jobs ON jobs;
DROP TABLE IF EXISTS jobs;
DROP SEQUENCE IF EXISTS jobs_id_seq;
