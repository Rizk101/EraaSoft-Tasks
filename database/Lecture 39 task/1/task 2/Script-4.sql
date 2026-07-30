-- 1. Display employee names and department names
SELECT employee_name, department_name
FROM Employees
JOIN Departments
USING (department_id);

-- 2. List all orders and their corresponding customer names
SELECT order_id, customer_name
FROM Orders
JOIN Customers
USING (customer_id);

-- 3. Retrieve product names and supplier names
SELECT product_name, supplier_name
FROM Products
JOIN Suppliers
USING (supplier_id);

-- 4. Show student names and course titles
SELECT student_name, course_title
FROM Students
JOIN Enrollments
USING (student_id)
JOIN Courses
USING (course_id);

-- 5. Display invoice numbers and product names
SELECT invoice_number, product_name
FROM Invoices
JOIN Products
USING (product_id);

-- 6. List project names and employee names
SELECT project_name, employee_name
FROM Projects
JOIN Project_Employees
USING (project_id)
JOIN Employees
USING (employee_id);
-- 7. Retrieve author names and book titles
SELECT author_name, book_title
FROM Authors
JOIN Books
USING (author_id);
-- 8. Show sales order details with employee names
SELECT order_id, employee_name
FROM Sales_Orders
JOIN Employees
USING (employee_id);
-- 9. Display course schedules and instructor names
SELECT schedule_time, instructor_name
FROM Course_Schedules
JOIN Instructors
USING (instructor_id);
-- 10. List transactions along with account holder names
SELECT transaction_id, account_holder_name
FROM Transactions
JOIN Accounts
USING (account_id);