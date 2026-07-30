-- 1. List all customers and all orders.
SELECT c.customer_name,
       o.order_id
FROM Customers c
FULL OUTER JOIN Orders o
ON c.customer_id = o.customer_id;


-- 2. Display all employees and all projects.
SELECT e.employee_name,
       p.project_name
FROM Employees e
FULL OUTER JOIN Projects_Assigned pa
ON e.employee_id = pa.employee_id
FULL OUTER JOIN Projects p
ON pa.project_id = p.project_id;


-- 3. Show all products and all suppliers.
SELECT p.product_name,
       s.supplier_name
FROM Products p
FULL OUTER JOIN Suppliers s
ON p.supplier_id = s.supplier_id;


-- 4. List all students and all courses.
SELECT s.student_name,
       c.course_name
FROM Students s
FULL OUTER JOIN Enrollments e
ON s.student_id = e.student_id
FULL OUTER JOIN Courses c
ON e.course_id = c.course_id;


-- 5. Retrieve all authors and all books.
SELECT a.author_name,
       b.book_title
FROM Authors a
FULL OUTER JOIN Books b
ON a.author_id = b.author_id;


-- 6. Find all employees and all departments.
SELECT e.employee_name,
       d.department_name
FROM Employees e
FULL OUTER JOIN Departments d
ON e.department_id = d.department_id;


-- 7. Show all transactions and all payment methods.
SELECT t.transaction_id,
       pm.payment_method_name
FROM Transactions t
FULL OUTER JOIN Payment_Methods pm
ON t.payment_method_id = pm.payment_method_id;


-- 8. Combine two customer lists from two different regions.
SELECT r1.customer_name AS Region1_Customer,
       r2.customer_name AS Region2_Customer
FROM Region1_Customers r1
FULL OUTER JOIN Region2_Customers r2
ON r1.customer_id = r2.customer_id;