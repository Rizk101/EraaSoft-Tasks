-- 1. Find the names of employees who earn more than the average salary.
SELECT employee_name
FROM Employees
WHERE salary > (
    SELECT AVG(salary)
    FROM Employees
);


-- 2. List the customers who placed the highest number of orders.
SELECT customer_name
FROM Customers
WHERE customer_id IN (
    SELECT customer_id
    FROM Orders
    GROUP BY customer_id
    HAVING COUNT(*) = (
        SELECT MAX(order_count)
        FROM (
            SELECT COUNT(*) AS order_count
            FROM Orders
            GROUP BY customer_id
        )
    )
);


-- 3. Retrieve all products whose price is higher than ANY product in the 'Accessories' category.
SELECT product_name, price
FROM Products
WHERE price > ANY (
    SELECT price
    FROM Products
    WHERE category = 'Accessories'
);


-- 4. Display employees who work in the same department as 'John Smith'.
SELECT employee_name
FROM Employees
WHERE department_id = (
    SELECT department_id
    FROM Employees
    WHERE employee_name = 'John Smith'
);


-- 5. Get all orders that were placed by customers from 'New York'.
SELECT *
FROM Orders
WHERE customer_id IN (
    SELECT customer_id
    FROM Customers
    WHERE city = 'New York'
);


-- 6. Find the departments that have no employees.
SELECT department_name
FROM Departments d
WHERE NOT EXISTS (
    SELECT 1
    FROM Employees e
    WHERE e.department_id = d.department_id
);


-- 7. List the students who are not enrolled in any course.
SELECT student_name
FROM Students s
WHERE NOT EXISTS (
    SELECT 1
    FROM Enrollments e
    WHERE e.student_id = s.student_id
);


-- 8. Retrieve the second highest salary from the Employees table.
SELECT MAX(salary) AS second_highest_salary
FROM Employees
WHERE salary < (
    SELECT MAX(salary)
    FROM Employees
);


-- 9. Display products that have a price greater than the average price of all products.
SELECT product_name, price
FROM Products
WHERE price > (
    SELECT AVG(price)
    FROM Products
);


-- 10. Find customers who have ordered all products in category 'A'.
SELECT customer_name
FROM Customers c
WHERE NOT EXISTS (
    SELECT product_id
    FROM Products
    WHERE category = 'A'
    MINUS
    SELECT product_id
    FROM Orders o
    JOIN Order_Details od
    ON o.order_id = od.order_id
    WHERE o.customer_id = c.customer_id
);