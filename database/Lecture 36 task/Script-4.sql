

/* ============================================================
   1. CHECK Constraint (Code Samples)
   ============================================================ */

-- 1. Create Employees table with age >= 18
CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    CONSTRAINT chk_age CHECK (age >= 18)
);

-- 2. Salary must be between 3000 and 10000
CREATE TABLE Staff (
    staff_id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    CONSTRAINT chk_salary CHECK (salary BETWEEN 3000 AND 10000)
);

-- 3. Add CHECK constraint to Products table
ALTER TABLE Products
ADD CONSTRAINT chk_price
CHECK (price > 0);

-- 4. Create Students table with grades A to F
CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    student_name VARCHAR(100),
    grade CHAR(1),
    CONSTRAINT chk_grade
    CHECK (grade IN ('A','B','C','D','E','F'))
);



/* ============================================================
   2. Adding Constraints via ALTER TABLE
   ============================================================ */

-- 1. Add NOT NULL constraint to email
ALTER TABLE Customers
ALTER COLUMN email SET NOT NULL;

-- 2. Add UNIQUE constraint
ALTER TABLE Users
ADD CONSTRAINT uk_username
UNIQUE (username);

-- 3. Add FOREIGN KEY
ALTER TABLE Orders
ADD CONSTRAINT fk_order_customer
FOREIGN KEY (customer_id)
REFERENCES Customers(id);

-- 4. Add CHECK constraint
ALTER TABLE Accounts
ADD CONSTRAINT chk_balance
CHECK (balance >= 0);

-- 5. Add PRIMARY KEY
ALTER TABLE Departments
ADD CONSTRAINT pk_departments
PRIMARY KEY (dept_id);



/* ============================================================
   3. Dropping (Removing) Constraints
   ============================================================ */

-- 1. Drop CHECK constraint
ALTER TABLE Employees
DROP CONSTRAINT chk_salary;

-- 2. Drop UNIQUE constraint
ALTER TABLE Users
DROP CONSTRAINT email;

-- (Alternative for PostgreSQL)
-- ALTER TABLE Users DROP CONSTRAINT uk_email;

-- 3. Drop PRIMARY KEY
ALTER TABLE Products
DROP PRIMARY KEY;

-- 4. Drop FOREIGN KEY
ALTER TABLE Orders
DROP CONSTRAINT fk_order_customer;

-- 5. Remove NOT NULL constraint
ALTER TABLE Contacts
ALTER COLUMN phone DROP NOT NULL;



/* ============================================================
   4. Renaming Constraints
   ============================================================ */

-- PostgreSQL Syntax

-- 1. Rename CHECK constraint
ALTER TABLE Students
RENAME CONSTRAINT chk_age TO check_min_age;

-- 2. Rename FOREIGN KEY
ALTER TABLE Employees
RENAME CONSTRAINT fk_emp_dept TO fk_employee_department;

-- 3. Rename PRIMARY KEY
ALTER TABLE Users
RENAME CONSTRAINT users_pkey TO pk_users_id;

-- 4. Rename UNIQUE constraint
ALTER TABLE Users
RENAME CONSTRAINT uk_username TO uk_user_name;

-- 5. Syntax comparison

-- SQL Server
EXEC sp_rename
'Students.chk_age',
'check_min_age',
'OBJECT';

-- PostgreSQL
ALTER TABLE Students
RENAME CONSTRAINT chk_age
TO check_min_age;



/* ============================================================
   5. Disabling Constraints
   ============================================================ */

-- SQL Server

-- 1. Disable FOREIGN KEY
ALTER TABLE Orders
NOCHECK CONSTRAINT fk_customer_order;

-- 2. Disable all constraints
ALTER TABLE Products
NOCHECK CONSTRAINT ALL;

-- 3. Disable CHECK constraint
ALTER TABLE Accounts
NOCHECK CONSTRAINT chk_balance;

-- 4. Disable PRIMARY KEY
-- (Primary Keys cannot be disabled directly.
-- They must be dropped if necessary.)

-- 5. Disable all constraints before bulk insert
ALTER TABLE YourTable
NOCHECK CONSTRAINT ALL;

-- Perform BULK INSERT here...



/* ============================================================
   6. Enabling Constraints
   ============================================================ */

-- SQL Server

-- 1. Enable FOREIGN KEY
ALTER TABLE Orders
CHECK CONSTRAINT fk_customer_order;

-- 2. Enable all constraints
ALTER TABLE Products
CHECK CONSTRAINT ALL;

-- 3. Enable CHECK constraint
ALTER TABLE Staff
CHECK CONSTRAINT chk_salary;

-- 4. Enable PRIMARY KEY
-- (If dropped, recreate it)

ALTER TABLE Departments
ADD CONSTRAINT pk_departments
PRIMARY KEY (dept_id);

-- 5. Enable only if disabled
IF EXISTS (
    SELECT *
    FROM sys.check_constraints
    WHERE name = 'chk_salary'
      AND is_disabled = 1
)
BEGIN
    ALTER TABLE Staff
    CHECK CONSTRAINT chk_salary;
END;


/* ============================================================
   End of File
   ============================================================ */