-- 1. Retrieve all employees and their department names
SELECT e.employee_name,
       d.department_name
FROM Employees e
LEFT OUTER JOIN Departments d
ON e.department_id = d.department_id;

-- 2. List all products and their associated categories
SELECT p.product_name,
       c.category_name
FROM Products p
LEFT OUTER JOIN Categories c
ON p.category_id = c.category_id;

-- 3. Find all students and the courses they are enrolled in
SELECT s.student_name,
       c.course_name
FROM Students s
LEFT OUTER JOIN Enrollments e
ON s.student_id = e.student_id
LEFT OUTER JOIN Courses c
ON e.course_id = c.course_id;

-- 4. Display all orders with customer names
SELECT o.order_id,
       c.customer_name
FROM Orders o
LEFT OUTER JOIN Customers c
ON o.customer_id = c.customer_id;

-- 5. Show all departments and their managers
SELECT d.department_name,
       e.employee_name AS manager_name
FROM Departments d
LEFT OUTER JOIN Employees e
ON d.manager_id = e.employee_id;

-- 6. List all books and their authors
SELECT b.book_title,
       a.author_name
FROM Books b
LEFT OUTER JOIN Authors a
ON b.author_id = a.author_id;

-- 7. Retrieve all invoices along with their payment status
SELECT i.invoice_id,
       p.payment_status
FROM Invoices i
LEFT OUTER JOIN Payments p
ON i.invoice_id = p.invoice_id;

-- 8. Get all employees and their projects
SELECT e.employee_name,
       pa.project_name
FROM Employees e
LEFT OUTER JOIN Projects_Assigned pa
ON e.employee_id = pa.employee_id;