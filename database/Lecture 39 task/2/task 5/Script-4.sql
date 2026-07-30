-- 1. Find the employee(s) with the highest salary.
SELECT employee_name, salary
FROM Employees
WHERE salary = (
    SELECT MAX(salary)
    FROM Employees
);


-- 2. List the names of employees who work in the same department as employee 'Alice'.
SELECT employee_name
FROM Employees
WHERE department_id = (
    SELECT department_id
    FROM Employees
    WHERE employee_name = 'Alice'
);


-- 3. Display the details of the product with the lowest price.
SELECT *
FROM Products
WHERE price = (
    SELECT MIN(price)
    FROM Products
);


-- 4. Retrieve the department name of the employee with the highest salary.
SELECT department_name
FROM Departments
WHERE department_id = (
    SELECT department_id
    FROM Employees
    WHERE salary = (
        SELECT MAX(salary)
        FROM Employees
    )
);


-- 5. Find the manager of the employee who was hired most recently.
SELECT manager_name
FROM Managers
WHERE manager_id = (
    SELECT manager_id
    FROM Employees
    WHERE hire_date = (
        SELECT MAX(hire_date)
        FROM Employees
    )
);


-- 6. Show the employee whose salary is equal to the average salary of the company.
SELECT employee_name, salary
FROM Employees
WHERE salary = (
    SELECT AVG(salary)
    FROM Employees
);


-- 7. List the order(s) with the earliest order date.
SELECT *
FROM Orders
WHERE order_date = (
    SELECT MIN(order_date)
    FROM Orders
);


-- 8. Get the name and salary of the employee who earns more than the employee with ID = 101.
SELECT employee_name, salary
FROM Employees
WHERE salary > (
    SELECT salary
    FROM Employees
    WHERE employee_id = 101
);


-- 9. Find the student who has the same GPA as student 'John Doe'.
SELECT student_name, gpa
FROM Students
WHERE gpa = (
    SELECT gpa
    FROM Students
    WHERE student_name = 'John Doe'
);


-- 10. Display all books that have the same price as the most expensive book in the 'Science' category.
SELECT book_title, price
FROM Books
WHERE price = (
    SELECT MAX(price)
    FROM Books
    WHERE category = 'Science'
);