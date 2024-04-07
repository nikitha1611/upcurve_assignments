CREATE DATABASE HR;
USE HR;

CREATE TABLE REGIONS (
    region_id INT PRIMARY KEY,
    region_name VARCHAR(50) NOT NULL
);

CREATE TABLE COUNTRIES (
    country_id VARCHAR(2) PRIMARY KEY,
    country_name VARCHAR(50) NOT NULL,
    region_id INT,
    FOREIGN KEY (region_id) REFERENCES REGIONS(region_id)
);

CREATE TABLE LOCATIONS (
    location_id INT PRIMARY KEY,
    street_address VARCHAR(255),
    postal_code VARCHAR(10),
    city VARCHAR(50),
    state_province VARCHAR(50),
    country_id VARCHAR(2),
    FOREIGN KEY (country_id) REFERENCES COUNTRIES(country_id)
);

CREATE TABLE JOBS (
    job_id VARCHAR(15) PRIMARY KEY,
    job_title VARCHAR(50),
    min_salary INT,
    max_salary INT
);

CREATE TABLE DEPARTMENTS (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50),
    manager_id INT,
    location_id INT,
    FOREIGN KEY (manager_id) REFERENCES EMPLOYEES(employee_id),
    FOREIGN KEY (location_id) REFERENCES LOCATIONS(location_id)
);

CREATE TABLE EMPLOYEES (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(50),
    hire_date DATE,
    job_id VARCHAR(15),
    salary DOUBLE,
    commission_pct DOUBLE,
    manager_id INT,
    department_id INT,
    FOREIGN KEY (job_id) REFERENCES JOBS(job_id),
    FOREIGN KEY (manager_id) REFERENCES EMPLOYEES(employee_id),
    FOREIGN KEY (department_id) REFERENCES DEPARTMENTS(department_id)
);

CREATE TABLE JOB_HISTORY (
    employee_id INT,
    start_date DATE,
    end_date DATE,
    job_id VARCHAR(15),
    department_id INT,
    PRIMARY KEY (employee_id, start_date),
    FOREIGN KEY (job_id) REFERENCES JOBS(job_id),
    FOREIGN KEY (department_id) REFERENCES DEPARTMENTS(department_id)
);
