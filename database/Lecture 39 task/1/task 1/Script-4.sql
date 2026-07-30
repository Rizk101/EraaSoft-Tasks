-- 1. Display employee names and their department names
SELECT employee_name, department_name
FROM Employees
NATURAL JOIN Departments;
-- 2. List all orders with their corresponding customer names
SELECT order_id, customer_name
FROM Orders
NATURAL JOIN Customers;
-- 3. Show student names and the courses they are enrolled in
SELECT student_name, course_name
FROM Students
NATURAL JOIN Enrollments
NATURAL JOIN Courses;

-- 4. Display project names and the employees working on them
SELECT project_name, employee_name
FROM Projects
NATURAL JOIN Project_Employees
NATURAL JOIN Employees;

-- 5. Retrieve invoice details along with product names
SELECT invoice_id, invoice_date, product_name
FROM Invoices
NATURAL JOIN Products;

-- 6. Find all books with their respective author names
SELECT book_title, author_name
FROM Books
NATURAL JOIN Authors;

-- 7. List all class schedules along with their instructors' names
SELECT class_name, schedule_time, instructor_name
FROM Class_Schedules
NATURAL JOIN Instructors;

-- 8. Show supplier names and the products they supply
SELECT supplier_name, product_name
FROM Suppliers
NATURAL JOIN Products;

-- 9. Display customer orders along with shipping details
SELECT order_id, customer_name, shipping_address, shipping_date
FROM Orders
NATURAL JOIN Customers
NATURAL JOIN Shipping;

-- 10. List employees along with their job titles
SELECT employee_name, job_title
FROM Employees
NATURAL JOIN Jobs;