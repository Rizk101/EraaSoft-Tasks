-- 1. List all departments and the employees working in them.
SELECT d.department_name,
       e.employee_name
FROM Employees e
RIGHT OUTER JOIN Departments d
ON e.department_id = d.department_id;


-- 2. Retrieve all orders and their corresponding customers.
SELECT o.order_id,
       c.customer_name
FROM Orders o
RIGHT OUTER JOIN Customers c
ON o.customer_id = c.customer_id;


-- 3. Show all courses and enrolled students.
SELECT c.course_name,
       s.student_name
FROM Enrollments e
RIGHT OUTER JOIN Courses c
ON e.course_id = c.course_id
LEFT JOIN Students s
ON e.student_id = s.student_id;


-- 4. Display all projects and the employees assigned to them.
SELECT p.project_name,
       e.employee_name
FROM Projects_Assigned pa
RIGHT OUTER JOIN Projects p
ON pa.project_id = p.project_id
LEFT JOIN Employees e
ON pa.employee_id = e.employee_id;


-- 5. Show all payment methods and their related transactions.
SELECT pm.payment_method_name,
       t.transaction_id
FROM Transactions t
RIGHT OUTER JOIN Payment_Methods pm
ON t.payment_method_id = pm.payment_method_id;


-- 6. Find all authors and their books.
SELECT a.author_name,
       b.book_title
FROM Books b
RIGHT OUTER JOIN Authors a
ON b.author_id = a.author_id;


-- 7. List all categories and the products under them.
SELECT c.category_name,
       p.product_name
FROM Products p
RIGHT OUTER JOIN Categories c
ON p.category_id = c.category_id;


-- 8. Retrieve all students and their assigned dorm rooms.
SELECT s.student_name,
       d.room_number
FROM Students s
RIGHT OUTER JOIN Dorm_Rooms d
ON s.room_id = d.room_id;