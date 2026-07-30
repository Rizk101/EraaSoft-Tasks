-- 1. Find all employees who earn more than at least one employee in department 10.
SELECT employee_name, salary
FROM Employees
WHERE salary > ANY (
    SELECT salary
    FROM Employees
    WHERE department_id = 10
);


-- 2. List the employees who earn less than all employees in department 20.
SELECT employee_name, salary
FROM Employees
WHERE salary < ALL (
    SELECT salary
    FROM Employees
    WHERE department_id = 20
);


-- 3. Display products that have a price equal to any product in the 'Electronics' category.
SELECT product_name, price
FROM Products
WHERE price IN (
    SELECT price
    FROM Products
    WHERE category = 'Electronics'
);


-- 4. Retrieve names of customers who have placed an order for a product with price greater than $1000.
SELECT customer_name
FROM Customers
WHERE customer_id IN (
    SELECT o.customer_id
    FROM Orders o
    JOIN Order_Details od
        ON o.order_id = od.order_id
    JOIN Products p
        ON od.product_id = p.product_id
    WHERE p.price > 1000
);


-- 5. List the employees who work in the same job titles as at least one other employee.
SELECT employee_name, job_title
FROM Employees
WHERE job_title IN (
    SELECT job_title
    FROM Employees
    GROUP BY job_title
    HAVING COUNT(*) > 1
);


-- 6. Find the departments that have more than one employee.
SELECT department_name
FROM Departments
WHERE department_id IN (
    SELECT department_id
    FROM Employees
    GROUP BY department_id
    HAVING COUNT(*) > 1
);


-- 7. Show all orders placed by customers who are from cities where other customers have placed orders too.
SELECT *
FROM Orders
WHERE customer_id IN (
    SELECT c.customer_id
    FROM Customers c
    WHERE c.city IN (
        SELECT c2.city
        FROM Customers c2
        JOIN Orders o2
            ON c2.customer_id = o2.customer_id
        GROUP BY c2.city
        HAVING COUNT(DISTINCT c2.customer_id) > 1
    )
);


-- 8. List all books that were written by authors who have published more than one book.
SELECT book_title
FROM Books
WHERE author_id IN (
    SELECT author_id
    FROM Books
    GROUP BY author_id
    HAVING COUNT(*) > 1
);


-- 9. Display the names of students who are enrolled in any of the courses taught by professor 'Dr. Smith'.
SELECT student_name
FROM Students
WHERE student_id IN (
    SELECT student_id
    FROM Enrollments
    WHERE course_id IN (
        SELECT course_id
        FROM Courses
        WHERE instructor_name = 'Dr. Smith'
    )
);


-- 10. Retrieve all employees whose salary matches any of the salaries in department 30.
SELECT employee_name, salary
FROM Employees
WHERE salary IN (
    SELECT salary
    FROM Employees
    WHERE department_id = 30
);