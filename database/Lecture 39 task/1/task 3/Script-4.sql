-- 1. Retrieve employee names and their manager names
SELECT e.employee_name AS Employee,
       m.employee_name AS Manager
FROM Employees e
JOIN Employees m
ON e.manager_id = m.employee_id;

-- 2. List customer names and salesperson names
SELECT c.name AS Customer_Name,
       e.name AS Salesperson_Name
FROM Customers c
JOIN Employees e
ON c.salesperson_id = e.employee_id;

-- 3. Display order IDs and product IDs
SELECT o.order_id,
       od.product_id
FROM Orders o
JOIN Order_Details od
ON o.order_id = od.order_id;

-- 4. Retrieve student names and instructor names
SELECT s.name AS Student_Name,
       i.name AS Instructor_Name
FROM Students s
JOIN Instructors i
ON s.instructor_id = i.instructor_id;

-- 5. Show employee salaries and department budgets
SELECT e.employee_name,
       e.salary,
       d.department_name,
       d.budget
FROM Employees e
JOIN Departments d
ON e.department_id = d.department_id;

-- 6. Display project names and task names
SELECT p.name AS Project_Name,
       t.name AS Task_Name
FROM Projects p
JOIN Tasks t
ON p.project_id = t.project_id;

-- 7. Retrieve course dates and exam dates
SELECT c.course_name,
       c.date AS Course_Date,
       e.date AS Exam_Date
FROM Courses c
JOIN Exams e
ON c.course_id = e.course_id;

-- 8. Show product name and category name
SELECT p.name AS Product_Name,
       c.name AS Category_Name
FROM Products p
JOIN Categories c
ON p.category_id = c.category_id;

-- 9. Display book title and publisher name
SELECT b.title AS Book_Title,
       p.name AS Publisher_Name
FROM Books b
JOIN Publishers p
ON b.publisher_id = p.publisher_id;

-- 10. List employee names and their department's location
SELECT e.employee_name,
       d.location AS Department_Location
FROM Employees e
JOIN Departments d
ON e.department_id = d.department_id;