--------------------------------------------------
-- 1. Character Functions – Part 3 (TRIM, LTRIM, RTRIM)
--------------------------------------------------

-- Create table
CREATE TABLE CUSTOMERS (
    FULL_NAME VARCHAR2(100)
);

-- Insert data
INSERT INTO CUSTOMERS VALUES ('   Karim Rizk   ');
INSERT INTO CUSTOMERS VALUES ('  Ahmed Ali');
INSERT INTO CUSTOMERS VALUES ('Mohamed Omar   ');
INSERT INTO CUSTOMERS VALUES ('   John Smith');

-- TRIM: Remove leading and trailing spaces
SELECT TRIM(FULL_NAME) FROM CUSTOMERS;

-- LTRIM: Remove leading spaces
SELECT LTRIM(FULL_NAME) FROM CUSTOMERS;

-- RTRIM: Remove trailing spaces
SELECT RTRIM(FULL_NAME) FROM CUSTOMERS;

-- Trim specific characters
SELECT TRIM('$' FROM '$$$Karim$$$') FROM DUAL;
SELECT TRIM('*' FROM '***Hello***') FROM DUAL;
SELECT TRIM('#' FROM '###Oracle###') FROM DUAL;


--------------------------------------------------
-- 2. Character Functions – Part 4 (REPLACE, LPAD, RPAD)
--------------------------------------------------

-- Replace characters
SELECT REPLACE('promotion', 'o', '0') FROM DUAL;

-- Replace a word
SELECT REPLACE('This is a basic course', 'basic', 'advanced')
FROM DUAL;

-- Create departments table
CREATE TABLE DEPARTMENTS (
    DEPT_NAME VARCHAR2(50)
);

-- Insert departments
INSERT INTO DEPARTMENTS VALUES ('HR');
INSERT INTO DEPARTMENTS VALUES ('IT');
INSERT INTO DEPARTMENTS VALUES ('Sales');

-- LPAD: Padding on the left
SELECT LPAD(DEPT_NAME, 15, '*')
FROM DEPARTMENTS;

-- RPAD: Padding on the right
SELECT RPAD(DEPT_NAME, 15, '-')
FROM DEPARTMENTS;


--------------------------------------------------
-- 3. TO_CHAR Function
--------------------------------------------------

-- Current date DD-MON-YYYY
SELECT TO_CHAR(SYSDATE, 'DD-MON-YYYY')
FROM DUAL;

-- Day, Month YYYY
SELECT TO_CHAR(SYSDATE, 'Day, Month YYYY')
FROM DUAL;

-- Number with commas and two decimal places
SELECT TO_CHAR(12345.67, '99,999.99')
FROM DUAL;

-- Employee salary with currency symbol
SELECT TO_CHAR(SALARY, 'L99,999.99')
FROM HR.EMPLOYEES;

-- System date YYYY/MM/DD HH24:MI:SS
SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS')
FROM DUAL;


--------------------------------------------------
-- 4. CASE Expressions
--------------------------------------------------

-- Create students table
CREATE TABLE STUDENTS (
    NAME VARCHAR2(50),
    SCORE NUMBER
);

-- Insert records
INSERT INTO STUDENTS VALUES ('Karim', 95);
INSERT INTO STUDENTS VALUES ('Ahmed', 85);
INSERT INTO STUDENTS VALUES ('Omar', 75);
INSERT INTO STUDENTS VALUES ('Ali', 65);
INSERT INTO STUDENTS VALUES ('Mohamed', 50);

-- Assign grades
SELECT NAME, SCORE,
       CASE
           WHEN SCORE >= 90 THEN 'A'
           WHEN SCORE >= 80 THEN 'B'
           WHEN SCORE >= 70 THEN 'C'
           ELSE 'F'
       END AS GRADE
FROM STUDENTS;

-- Pass or Fail
SELECT NAME, SCORE,
       CASE
           WHEN SCORE >= 60 THEN 'Pass'
           ELSE 'Fail'
       END AS RESULT
FROM STUDENTS;

-- Display messages based on grades
SELECT NAME, SCORE,
       CASE
           WHEN SCORE >= 90 THEN 'Excellent'
           WHEN SCORE >= 80 THEN 'Good'
           WHEN SCORE >= 70 THEN 'Average'
           ELSE 'Needs Improvement'
       END AS MESSAGE
FROM STUDENTS;

-- Display today's day
SELECT CASE
           WHEN TO_CHAR(SYSDATE, 'D') = '1' THEN 'Today is Sunday'
           WHEN TO_CHAR(SYSDATE, 'D') = '2' THEN 'Today is Monday'
           WHEN TO_CHAR(SYSDATE, 'D') = '3' THEN 'Today is Tuesday'
           WHEN TO_CHAR(SYSDATE, 'D') = '4' THEN 'Today is Wednesday'
           WHEN TO_CHAR(SYSDATE, 'D') = '5' THEN 'Today is Thursday'
           WHEN TO_CHAR(SYSDATE, 'D') = '6' THEN 'Today is Friday'
           ELSE 'Today is Saturday'
       END AS TODAY
FROM DUAL;


--------------------------------------------------
-- 5. DECODE Function
--------------------------------------------------

-- Convert specific scores to grades
SELECT NAME, SCORE,
       DECODE(SCORE,
           100, 'A',
           90, 'B',
           80, 'C',
           'F'
       ) AS GRADE
FROM STUDENTS;

-- Create status_log table
CREATE TABLE STATUS_LOG (
    STATUS_CODE CHAR(1)
);

-- Insert statuses
INSERT INTO STATUS_LOG VALUES ('N');
INSERT INTO STATUS_LOG VALUES ('I');
INSERT INTO STATUS_LOG VALUES ('C');

-- Convert status codes
SELECT STATUS_CODE,
       DECODE(STATUS_CODE,
           'N', 'New',
           'I', 'In Progress',
           'C', 'Completed',
           'Unknown'
       ) AS STATUS
FROM STATUS_LOG;

-- Check product quantity
SELECT PRODUCT_NAME, QUANTITY,
       DECODE(QUANTITY,
           0, 'Out of Stock',
           'Available'
       ) AS STOCK_STATUS
FROM PRODUCTS;

-- Display bonus based on department
SELECT FIRST_NAME, DEPARTMENT_ID,
       DECODE(DEPARTMENT_ID,
           10, 500,
           20, 1000,
           30, 1500,
           300
       ) AS BONUS
FROM HR.EMPLOYEES;

COMMIT;