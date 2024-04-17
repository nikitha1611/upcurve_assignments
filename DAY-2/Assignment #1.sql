USE HR;

-- Creating the job_grades table
CREATE TABLE job_grades (
    grade_level VARCHAR(2) NOT NULL,
    lowest_sal DECIMAL(10,2),
    highest_sal DECIMAL(10,2),
    PRIMARY KEY (grade_level)
);

-- Populating the job_grades table with example data
INSERT INTO job_grades (grade_level, lowest_sal, highest_sal) VALUES
('A', 0, 2999),
('B', 3000, 5999),
('C', 6000, 9999),
('D', 10000, 14999),
('E', 15000, 24999),
('F', 25000, 39999),
('G', 40000, 99999);

-- 1. Display the lastname, department number, and department name for all employees.
SELECT e.lastname, e.department_id, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id;

-- 2. Create a unique listing of all jobs that are in department 30. Include the location id in the output.
SELECT DISTINCT e.job_id, d.location_id
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE e.department_id = 30;

-- 3. Display the employee lastname, department name, location id, and city of all employees who earn a commission.
SELECT e.lastname, d.department_name, l.location_id, l.city
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
WHERE e.commission_pct IS NOT NULL;

-- 4. Display the employee lastname, and department name for all employees who have an "a" in their lastname.
SELECT e.lastname, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE e.lastname LIKE '%a%';

-- 5. Write a query to display the lastname, job, department number, and department name for all employees who work in Toronto.
SELECT e.lastname, e.job_id, e.department_id, d.department_name
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id
WHERE l.city = 'Toronto';

-- 6. Display the employee lastname and employee number along with their manager's lastname and manager number.
-- Label the columns "Employee", "Emp#", "Manager" and "Manager#" respectively.
SELECT e.lastname AS Employee, e.employee_id AS Emp#, m.lastname AS Manager, e.manager_id AS Manager#
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id;

-- 7. Modify the above to display the same columns for all employees (including "King", who has no manager).
-- Order the result by the employee number.
SELECT e.lastname AS Employee, e.employee_id AS Emp#, m.lastname AS Manager, e.manager_id AS Manager#
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.employee_id
ORDER BY e.employee_id;

-- 8. Create a query that displays employee lastnames, department numbers, and all the
-- employees who work in the same department as a given employee. Give each column an appropriate label.
SELECT e1.lastname AS Employee, e1.department_id AS Department,
       e2.lastname AS Colleague
FROM employees e1
JOIN employees e2 ON e1.department_id = e2.department_id
WHERE e1.lastname = 'Given Employee Lastname';

-- 9. Create a query that displays the name, job, department name, salary and grade for all employees.
SELECT e.firstname, e.lastname, e.job_id, d.department_name, e.salary, g.grade_level
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN job_grades g ON e.salary BETWEEN g.lowest_sal AND g.highest_sal;

-- 10. Create a query to display the name and hiredate of any employee hired after employee "Davies".
SELECT e.firstname, e.lastname, e.hire_date
FROM employees e
WHERE e.hire_date > (SELECT hire_date FROM employees WHERE lastname = 'Davies');

-- 11. Display the names and hire dates for all employees who were hired before their managers,
-- along with their manager's names and hire dates. Label the columns "Employee", "Emp hired", "Manager", and "Manager hired" respectively.
SELECT e.firstname AS Employee, e.hire_date AS Emp_hired,
       m.firstname AS Manager, m.hire_date AS Manager_hired
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
WHERE e.hire_date < m.hire_date;

-- 12. Display the highest, lowest, sum and average salary of all employees. Label the columns "Maximum", "Minimum", "Sum", and "Average" respectively.
SELECT MAX(salary) AS Maximum, MIN(salary) AS Minimum, SUM(salary) AS Sum, AVG(salary) AS Average
FROM employees;

-- 13. Modify the above query to display the same data for each job type.
SELECT job_id, MAX(salary) AS Maximum, MIN(salary) AS Minimum, SUM(salary) AS Sum, AVG(salary) AS Average
FROM employees
GROUP BY job_id;

-- 14. Write a query to display the number of people with the same job.
SELECT job_id, COUNT(*) AS Number_of_People
FROM employees
GROUP BY job_id;

-- 15. Determine the number of managers without listing them. Label the column "Number of Managers".
SELECT COUNT(DISTINCT manager_id) AS "Number of Managers"
FROM employees
WHERE manager_id IS NOT NULL;

-- 16. Write a query that displays the difference between the highest and the lowest salaries.
-- Label the column as "Difference".
SELECT MAX(salary) - MIN(salary) AS Difference
FROM employees;

-- 17. Display the manager number and the salary of the lowest paid employee for that manager.
-- Exclude anyone whose manager is not known. Exclude any group where the minimum salary is less than $6000.
-- Sort the output in descending order of salary.
SELECT manager_id, MIN(salary) AS Min_Salary
FROM employees
WHERE manager_id IS NOT NULL AND salary >= 6000
GROUP BY manager_id
ORDER BY Min_Salary DESC;

-- 18. Write a query to display each department's name, location, number of employees, and the average salary for all employees in that department.
-- Label the columns "Name", "Location", "No.of people", and "Salary" respectively. Round the average salary to two decimal places.
SELECT d.department_name AS Name, l.city AS Location, COUNT(e.employee_id) AS "No.of people", ROUND(AVG(e.salary), 2) AS Salary
FROM departments d
JOIN employees e ON d.department_id = e.department_id
JOIN locations l ON d.location_id = l.location_id
GROUP BY d.department_name, l.city;

-- 19. Write a query to display the lastname, and hiredate of any employee in the department as the employee "Zlotkey". Exclude "Zlotkey".
SELECT e.lastname, e.hire_date
FROM employees e
JOIN employees z ON e.department_id = z.department_id
WHERE z.lastname = 'Zlotkey' AND e.lastname <> 'Zlotkey';

-- 20. Create a query to display the employee numbers and lastnames of all employees who earn more than the average salary. Sort the result in ascending order of salary.
SELECT employee_id, lastname
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees)
ORDER BY salary ASC;

-- 21. Write a query that displays the employee number and lastname of all employees who work in a department with any employee whose lastname contains a "u".
SELECT DISTINCT e1.employee_id, e1.lastname
FROM employees e1
JOIN employees e2 ON e1.department_id = e2.department_id
WHERE e2.lastname LIKE '%u%';

-- 22. Display the lastname, department number, and job id of all employees whose department location id is 1700.
SELECT e.lastname, e.department_id, e.job_id
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE d.location_id = 1700;

-- 23. Display the lastname and salary of every employee who reports to "King".
SELECT e.lastname, e.salary
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
WHERE m.lastname = 'King';

-- 24. Display the department number, lastname, and job id for every employee in the "Executive" department.
SELECT e.department_id, e.lastname, e.job_id
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE d.department_name = 'Executive';

-- 25. Display the employee number, lastname, and salary of all employees who earn more than the average salary and who work in a department with any employee with a "u" in their lastname.
SELECT e.employee_id, e.lastname, e.salary
FROM employees e
JOIN departments d ON e.department_id = d.department_id
WHERE e.salary > (SELECT AVG(salary) FROM employees) AND EXISTS (
    SELECT 1 FROM employees e2 WHERE e2.department_id = d.department_id AND e2.lastname LIKE '%u%'
);

-- 26. Write a query to get unique department ID from employee table.
SELECT DISTINCT department_id
FROM employees;

-- 27. Write a query to get all employee details from the employee table order by first name, descending.
SELECT *
FROM employees
ORDER BY firstname DESC;

-- 28. Write a query to get the names (first_name, last_name), salary, PF of all the employees (PF is calculated as 15% of salary).
SELECT first_name, last_name, salary, ROUND(0.15 * salary, 2) AS PF
FROM employees;

-- 29. Write a query to get the employee ID, names (first_name, last_name), salary in ascending order of salary.
SELECT employee_id, first_name, last_name, salary
FROM employees
ORDER BY salary ASC;

-- 30. Write a query to get the total salaries payable to employees.
SELECT SUM(salary) AS Total_Salaries
FROM employees;

-- 31. Write a query to get the maximum and minimum salary from employees table.
SELECT MAX(salary) AS Maximum_Salary, MIN(salary) AS Minimum_Salary
FROM employees;

-- 32. Write a query to get the average salary and number of employees in the employees table.
SELECT AVG(salary) AS Average_Salary, COUNT(*) AS Number_of_Employees
FROM employees;

-- 33. Write a query to get the number of employees working with the company.
SELECT COUNT(*) AS Total_Employees
FROM employees;

-- 34. Write a query to get the number of jobs available in the employees table.
SELECT COUNT(DISTINCT job_id) AS Job_Count
FROM employees;

-- 35. Write a query get all first name from employees table in upper case.
SELECT UPPER(first_name) AS First_Name
FROM employees;

-- 36. Write a query to get the first 3 characters of first name from employees table.
SELECT SUBSTRING(first_name, 1, 3) AS First_Three_Characters
FROM employees;

-- 37. Write a query to get the names (for example Ellen Abel, Sundar Ande etc.) of all the employees from employees table.
SELECT CONCAT(first_name, ' ', last_name) AS Full_Name
FROM employees;

-- 38. Write a query to get first name from employees table after removing white spaces from both sides.
SELECT TRIM(first_name) AS Trimmed_First_Name
FROM employees;

-- 39. Write a query to get the length of the employee names (first_name, last_name) from employees table.
SELECT first_name, last_name, LENGTH(CONCAT(first_name, ' ', last_name)) AS Name_Length
FROM employees;

-- 40. Write a query to check if the first_name fields of the employees table contains numbers.
SELECT first_name
FROM employees
WHERE first_name RLIKE '[0-9]';

-- 41. Write a query to select first 10 records from a table.
SELECT *
FROM employees
LIMIT 10;

-- 42. Write a query to get monthly salary (round 2 decimal places) of each and every employee.
SELECT employee_id, first_name, last_name, ROUND(salary / 12, 2) AS Monthly_Salary
FROM employees;

-- 43. Write a query to display the name (first_name, last_name) and salary for all employees whose salary is not in the range $10,000 through $15,000.
SELECT first_name, last_name, salary
FROM employees
WHERE salary NOT BETWEEN 10000 AND 15000;

-- 44. Write a query to display the name (first_name, last_name) and department ID of all employees in departments 30 or 100 in ascending order.
SELECT first_name, last_name, department_id
FROM employees
WHERE department_id IN (30, 100)
ORDER BY department_id ASC;

-- 45. Write a query to display the name (first_name, last_name) and salary for all employees whose salary is not in the range $10,000 through $15,000 and are in department 30 or 100.
SELECT first_name, last_name, salary
FROM employees
WHERE salary NOT BETWEEN 10000 AND 15000 AND department_id IN (30, 100);

-- 46. Write a query to display the name (first_name, last_name) and hire date for all employees who were hired in 1987.
SELECT first_name, last_name, hire_date
FROM employees
WHERE YEAR(hire_date) = 1987;

-- 47. Write a query to display the first_name of all employees who have both "b" and "c" in their first name.
SELECT first_name
FROM employees
WHERE first_name LIKE '%b%' AND first_name LIKE '%c%';

-- 48. Write a query to display the last name, job, and salary for all employees whose job is that of a Programmer or a Shipping Clerk, and whose salary is not equal to $4,500, $10,000, or $15,000.
SELECT last_name, job_id, salary
FROM employees
WHERE job_id IN ('Programmer', 'Shipping Clerk') AND salary NOT IN (4500, 10000, 15000);

-- 49. Write a query to display the last name of employees whose names have exactly 6 characters.
SELECT last_name
FROM employees
WHERE LENGTH(last_name) = 6;

-- 50. Write a query to display the last name of employees having 'e' as the third character.
SELECT last_name
FROM employees
WHERE SUBSTRING(last_name, 3, 1) = 'e';

-- 51. Display the jobs/designations available in the employees table.
SELECT DISTINCT job_id AS Job_Designation
FROM employees;

-- 52. Display the name (first_name, last_name), salary, and PF (15% of salary) of all employees.
SELECT first_name, last_name, salary, ROUND(salary * 0.15, 2) AS PF
FROM employees;

-- 53. Write a query to select all records from employees where last name is 'BLAKE', 'SCOTT', 'KING', or 'FORD'.
SELECT *
FROM employees
WHERE last_name IN ('BLAKE', 'SCOTT', 'KING', 'FORD');

-- 54. Write a query to list the number of jobs available in the employees table.
SELECT COUNT(DISTINCT job_id) AS Number_of_Jobs
FROM employees;

-- 55. Write a query to get the total salaries payable to employees.
SELECT SUM(salary) AS Total_Salaries
FROM employees;

-- 56. Write a query to get the minimum salary from employees table.
SELECT MIN(salary) AS Minimum_Salary
FROM employees;

-- 57. Write a query to get the maximum salary of an employee working as a Programmer.
SELECT MAX(salary) AS Maximum_Salary
FROM employees
WHERE job_id = 'Programmer';

-- 58. Write a query to get the average salary and number of employees working in the department 90.
SELECT AVG(salary) AS Average_Salary, COUNT(*) AS Number_of_Employees
FROM employees
WHERE department_id = 90;

-- 59. Write a query to get the highest, lowest, sum, and average salary of all employees.
SELECT MAX(salary) AS Highest, MIN(salary) AS Lowest, SUM(salary) AS Sum, AVG(salary) AS Average
FROM employees;

-- 60. Write a query to get the number of employees with the same job.
SELECT job_id, COUNT(*) AS Number_of_Employees
FROM employees
GROUP BY job_id;

-- 61. Write a query to get the difference between the highest and lowest salaries.
SELECT MAX(salary) - MIN(salary) AS Salary_Difference
FROM employees;

-- 62. Write a query to find the manager ID and the salary of the lowest-paid employee for that manager.
SELECT manager_id, MIN(salary) AS Min_Salary
FROM employees
GROUP BY manager_id;

-- 63. Write a query to get the department ID and the total salary payable in each department.
SELECT department_id, SUM(salary) AS Total_Salary
FROM employees
GROUP BY department_id;

-- 64. Write a query to get the average salary for each job ID excluding programmers.
SELECT job_id, AVG(salary) AS Average_Salary
FROM employees
WHERE job_id <> 'Programmer'
GROUP BY job_id;

-- 65. Write a query to get the total salary, maximum, minimum, and average salary of employees (job ID wise), for department ID 90 only.
SELECT job_id, SUM(salary) AS Total_Salary, MAX(salary) AS Max_Salary, MIN(salary) AS Min_Salary, AVG(salary) AS Avg_Salary
FROM employees
WHERE department_id = 90
GROUP BY job_id;

-- 66. Write a query to get the job ID and maximum salary of the employees where maximum salary is greater than or equal to $4000.
SELECT job_id, MAX(salary) AS Max_Salary
FROM employees
GROUP BY job_id
HAVING Max_Salary >= 4000;

-- 67. Write a query to get the average salary for all departments employing more than 10 employees.
SELECT department_id, AVG(salary) AS Average_Salary
FROM employees
GROUP BY department_id
HAVING COUNT(employee_id) > 10;

-- 68. Write a query to find the name (first_name, last_name) and the salary of the employees who have a higher salary than the employee whose last_name='Bull'.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > (SELECT salary FROM employees WHERE last_name = 'Bull');

-- 69. Write a query to find the name (first_name, last_name) of all employees who work in the IT department.
SELECT first_name, last_name
FROM employees
JOIN departments ON employees.department_id = departments.department_id
WHERE departments.department_name = 'IT';

-- 70. Write a query to find the name (first_name, last_name) of the employees who have a manager and work in a USA based department. (Hint: Write single-row and multiple-row subqueries)
SELECT first_name, last_name
FROM employees
WHERE manager_id IS NOT NULL AND department_id IN (
    SELECT department_id
    FROM departments
    JOIN locations ON departments.location_id = locations.location_id
    WHERE country_id = 'US'
);

-- 71. Write a query to find the name (first_name, last_name) of the employees who are managers.
SELECT first_name, last_name
FROM employees
WHERE employee_id IN (SELECT manager_id FROM employees WHERE manager_id IS NOT NULL);

-- 72. Write a query to find the name (first_name, last_name), and salary of the employees whose salary is greater than the average salary.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- 73. Write a query to find the name (first_name, last_name), and salary of the employees whose salary is equal to the minimum salary for their job grade.
SELECT first_name, last_name, salary
FROM employees
JOIN job_grades ON employees.salary BETWEEN job_grades.lowest_sal AND job_grades.highest_sal
WHERE employees.salary = job_grades.lowest_sal;

-- 74. Write a query to find the name (first_name, last_name), and salary of the employees who earn more than the average salary and work in any of the IT departments.
SELECT first_name, last_name, salary
FROM employees
JOIN departments ON employees.department_id = departments.department_id
WHERE salary > (SELECT AVG(salary) FROM employees) AND departments.department_name = 'IT';

-- 75. Write a query to find the name (first_name, last_name), and salary of the employees who earn more than Mr. Bell.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > (SELECT salary FROM employees WHERE last_name = 'Bell');

-- 76. Write a query to find the name (first_name, last_name), and salary of the employees who earn the same salary as the minimum salary for all departments.
SELECT first_name, last_name, salary
FROM employees
WHERE salary = (SELECT MIN(salary) FROM employees);

-- 77. Write a query to find the name (first_name, last_name), and salary of the employees whose salary is greater than the average salary of all departments.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);

-- 78. Write a query to find the name (first_name, last_name) and salary of the employees who earn a salary that is higher than the salary of all the Shipping Clerks (JOB_ID = 'SH_CLERK'). Sort the results from the salary of the lowest to highest.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > ALL (SELECT salary FROM employees WHERE job_id = 'SH_CLERK')
ORDER BY salary ASC;

-- 79. Write a query to find the name (first_name, last_name) of the employees who are not supervisors.
SELECT first_name, last_name
FROM employees
WHERE employee_id NOT IN (SELECT DISTINCT manager_id FROM employees WHERE manager_id IS NOT NULL);

-- 80. Write a query to display the employee ID, first name, last name, and department names of all employees.
SELECT employees.employee_id, employees.first_name, employees.last_name, departments.department_name
FROM employees
JOIN departments ON employees.department_id = departments.department_id;

-- 81. Write a query to display the employee ID, first name, last name, salary of all employees whose salary is above average for their departments.
SELECT employee_id, first_name, last_name, salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees e
    WHERE e.department_id = employees.department_id
);

-- 82. Write a query to fetch even numbered records from employees table.
SELECT *
FROM (
    SELECT *, ROW_NUMBER() OVER (ORDER BY employee_id) AS rownum
    FROM employees
) AS numbered_employees
WHERE mod(rownum, 2) = 0;

-- 83. Write a query to find the 5th maximum salary in the employees table.
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 4;

-- 84. Write a query to find the 4th minimum salary in the employees table.
SELECT DISTINCT salary
FROM employees
ORDER BY salary
LIMIT 1 OFFSET 3;

-- 85. Write a query to select the last 10 records from a table.
SELECT *
FROM employees
ORDER BY employee_id DESC
LIMIT 10;

-- 86. Write a query to list the department ID and name of all the departments where no employee is working.
SELECT departments.department_id, departments.department_name
FROM departments
LEFT JOIN employees ON departments.department_id = employees.department_id
WHERE employees.employee_id IS NULL;

-- 87. Write a query to get 3 maximum salaries.
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 3;

-- 88. Write a query to get 3 minimum salaries.
SELECT DISTINCT salary
FROM employees
ORDER BY salary
LIMIT 3;

-- 89. Write a query to get nth max salaries of employees.
-- Note: Replace 'n' with the actual number you want to find.
SELECT DISTINCT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET (n-1); -- Replace n with the actual rank number you want.

-- 90. Write a query to find the addresses (location_id, street_address, city, state_province, country_name) of all the departments.
SELECT locations.location_id, locations.street_address, locations.city, locations.state_province, countries.country_name
FROM locations
JOIN countries ON locations.country_id = countries.country_id;

-- 91. Write a query to find the name (first_name, last name), department ID, and name of all the employees.
SELECT employees.first_name, employees.last_name, departments.department_id, departments.department_name
FROM employees
JOIN departments ON employees.department_id = departments.department_id;

-- 92. Write a query to find the name (first_name, last_name), job, department ID, and name of the employees who work in London.
SELECT employees.first_name, employees.last_name, employees.job_id, departments.department_id, departments.department_name
FROM employees
JOIN departments ON employees.department_id = departments.department_id
JOIN locations ON departments.location_id = locations.location_id
WHERE locations.city = 'London';

-- 93. Write a query to find the employee id, name (last_name) along with their manager_id, and name (last_name).
SELECT e.employee_id, e.last_name, e.manager_id, m.last_name AS manager_last_name
FROM employees e
LEFT JOIN employees m ON e.manager_id = m.employee_id;

-- 94. Write a query to find the name (first_name, last_name) and hire date of the employees who were hired after 'Jones'.
SELECT first_name, last_name, hire_date
FROM employees
WHERE hire_date > (SELECT hire_date FROM employees WHERE last_name = 'Jones');

-- 95. Write a query to get the department name and number of employees in the department.
SELECT departments.department_name, COUNT(employees.employee_id) AS Number_of_Employees
FROM departments
JOIN employees ON departments.department_id = employees.department_id
GROUP BY departments.department_name;

-- 96. Write a query to find the employee ID, job title, number of days between ending date and starting date for all jobs in department 90.
SELECT employee_id, job_id AS Job_Title, DATEDIFF(job_history.end_date, job_history.start_date) AS Days_Between
FROM job_history
JOIN employees ON job_history.employee_id = employees.employee_id
WHERE employees.department_id = 90;

-- 97. Write a query to display the department ID and name and first name of the manager.
SELECT departments.department_id, departments.department_name, employees.first_name AS Manager_First_Name
FROM departments
JOIN employees ON departments.manager_id = employees.employee_id;

-- 98. Write a query to display the department name, manager name, and city.
SELECT departments.department_name, employees.first_name AS Manager_First_Name, locations.city
FROM departments
JOIN employees ON departments.manager_id = employees.employee_id
JOIN locations ON departments.location_id = locations.location_id;

-- 99. Write a query to display the job title and average salary of employees.
SELECT job_id AS Job_Title, AVG(salary) AS Average_Salary
FROM employees
GROUP BY job_id;

-- 100. Write a query to display job title, employee name, and the difference between salary of the employee and minimum salary for the job.
SELECT job_id AS Job_Title, CONCAT(first_name, ' ', last_name) AS Employee_Name, (salary - (SELECT MIN(salary) FROM employees WHERE job_id = employees.job_id)) AS Salary_Difference
FROM employees;

-- 101. Write a query to display the job history that were done by any employee who is currently drawing more than $10,000 of salary.
SELECT job_history.*
FROM job_history
JOIN employees ON job_history.employee_id = employees.employee_id
WHERE employees.salary > 10000;

-- 102. Write a query to display department name, name (first_name, last_name), hire date, salary of the manager for all managers whose experience is more than 15 years.
SELECT departments.department_name, CONCAT(managers.first_name, ' ', managers.last_name) AS Manager_Name, managers.hire_date, managers.salary
FROM departments
JOIN employees managers ON departments.manager_id = managers.employee_id
WHERE YEAR(CURRENT_DATE) - YEAR(managers.hire_date) > 15;

-- 103. Write a query to get the first name and hire date from employees table where hire date is between '1987-06-01' and '1987-07-30'.
SELECT first_name, hire_date
FROM employees
WHERE hire_date BETWEEN '1987-06-01' AND '1987-07-30';

-- 104. Write a query to get the firstname, lastname who joined in the month of June.
SELECT first_name, last_name
FROM employees
WHERE MONTH(hire_date) = 6;

-- 105. Write a query to get the years in which more than 10 employees joined.
SELECT YEAR(hire_date) AS Year, COUNT(*) AS Number_of_Employees
FROM employees
GROUP BY YEAR(hire_date)
HAVING COUNT(*) > 10;

-- 106. Write a query to get first name of employees who joined in 1987.
SELECT first_name
FROM employees
WHERE YEAR(hire_date) = 1987;

-- 107. Write a query to get department name, manager name, and salary of the manager for all managers whose experience is more than 5 years.
SELECT departments.department_name, CONCAT(managers.first_name, ' ', managers.last_name) AS Manager_Name, managers.salary
FROM departments
JOIN employees managers ON departments.manager_id = managers.employee_id
WHERE YEAR(CURRENT_DATE) - YEAR(managers.hire_date) > 5;

-- 108. Write a query to get employee ID, last name, and date of first salary of the employees.
-- Note: This query assumes 'first salary' can be interpreted from the employees' table directly or any other table representing salary changes is not specified.
SELECT employee_id, last_name, hire_date AS Date_of_First_Salary
FROM employees;

-- 109. Write a query to get first name, hire date and experience of the employees.
SELECT first_name, hire_date, YEAR(CURRENT_DATE) - YEAR(hire_date) AS Experience_Years
FROM employees;

-- 110. Write a query to get the department ID, year, and number of employees joined.
SELECT department_id, YEAR(hire_date) AS Year, COUNT(*) AS Number_of_Employees
FROM employees
GROUP BY department_id, YEAR(hire_date);

-- 111. Write a query to get the job_id and related employee's id.
SELECT job_id, employee_id
FROM employees;

-- 112. Write a query to update the portion of the phone_number in the employees table, within the phone number the substring '124' will be replaced by '999'.
-- Note: This query assumes you are permitted to execute UPDATE operations; here is how it would typically be written:
UPDATE employees
SET phone_number = REPLACE(phone_number, '124', '999')
WHERE phone_number LIKE '%124%';

-- 113. Write a query to get the details of the employees where the length of the first name is greater than or equal to 8.
SELECT *
FROM employees
WHERE LENGTH(first_name) >= 8;

-- 114. Write a query to display leading zeros before maximum and minimum salary.
SELECT LPAD(CAST(MAX(salary) AS CHAR), 10, '0') AS Max_Salary, LPAD(CAST(MIN(salary) AS CHAR), 10, '0') AS Min_Salary
FROM employees;

-- 115. Write a query to append '@example.com' to email field.
-- Note: This query will display the results with appended emails, not update them in the table.
SELECT CONCAT(email, '@example.com') AS Email
FROM employees;

-- 116. Write a query to get the employee id, first name, and hire month.
SELECT employee_id, first_name, MONTH(hire_date) AS Hire_Month
FROM employees;

-- 117. Write a query to get the employee id, email id (discard the last three characters).
SELECT employee_id, SUBSTRING(email, 1, LENGTH(email) - 3) AS Email
FROM employees;

-- 118. Write a query to find all employees where first names are in upper case.
SELECT *
FROM employees
WHERE BINARY first_name = UPPER(first_name);

-- 119. Write a query to extract the last 4 character of phone numbers.
SELECT RIGHT(phone_number, 4) AS Last_Four_Digits
FROM employees;

-- 120. Write a query to get the last word of the street address.
-- This assumes the last word can be identified after the last space.
SELECT SUBSTRING_INDEX(street_address, ' ', -1) AS Last_Word
FROM locations;

-- 121. Write a query to get the locations that have minimum street length.
SELECT *
FROM locations
WHERE LENGTH(street_address) = (SELECT MIN(LENGTH(street_address)) FROM locations);

-- 122. Write a query to display the first word from those job titles which contains more than one word.
SELECT SUBSTRING_INDEX(job_title, ' ', 1) AS First_Word
FROM jobs
WHERE job_title LIKE '% %';

-- 123. Write a query to display the length of first name for employees where last name contain character 'c' after 2nd position.
SELECT first_name, LENGTH(first_name) AS Length
FROM employees
WHERE last_name LIKE '__%c%';

-- 124. Write a query that displays the first name and the length of the first name for all employees whose name starts with the letters 'A', 'J', or 'M'.
-- Give each column an appropriate label. Sort the results by the employees' first names.
SELECT first_name, LENGTH(first_name) AS Length
FROM employees
WHERE first_name LIKE 'A%' OR first_name LIKE 'J%' OR first_name LIKE 'M%'
ORDER BY first_name;

-- 125. Write a query to display the first name and salary for all employees. Format the salary to be 10 characters long, left-padded with the $ symbol. Label the column SALARY.
SELECT first_name, LPAD(CONCAT('$', salary), 10, '$') AS SALARY
FROM employees;

-- 126. Write a query to display the first eight characters of the employees' first names and indicates the amounts of their salaries with '$' sign. Each '$' sign signifies a thousand dollars.
SELECT SUBSTRING(first_name, 1, 8) AS First_Name, REPEAT('$', FLOOR(salary / 1000)) AS Salary_Indication
FROM employees
ORDER BY salary DESC;

-- 127. Write a query to display the employees with their code, first name, last name and hire date who hired either on the seventh day of any month or seventh month in any year.
SELECT employee_id AS Code, first_name, last_name, hire_date
FROM employees
WHERE DAY(hire_date) = 7 OR MONTH(hire_date) = 7;




