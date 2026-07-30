--====================================================
-- 1. Create User
--====================================================
CREATE USER YOUR_NAME IDENTIFIED BY your_password;

--====================================================
-- 2. Grant Privileges
--====================================================
GRANT CREATE SESSION TO YOUR_NAME;

GRANT CREATE TABLE TO YOUR_NAME;

GRANT INSERT, SELECT, UPDATE, DELETE ON HR.STUDENT TO YOUR_NAME;

--====================================================
-- 3. Connect as the new user
--====================================================
CONNECT YOUR_NAME/your_password;

--====================================================
-- Create Student Table
--====================================================
CREATE TABLE Student (
    id   NUMBER PRIMARY KEY,
    name VARCHAR2(100)
);

--====================================================
-- Insert into Student
--====================================================
INSERT INTO Student (id, name)
VALUES (1, 'Ahmed');

COMMIT;

--====================================================
-- Select from Student
--====================================================
SELECT * FROM Student;

--====================================================
-- Update Student
--====================================================
UPDATE Student
SET name = 'Mohamed'
WHERE id = 1;

COMMIT;

--====================================================
-- Delete from Student
--====================================================
DELETE FROM Student
WHERE id = 1;

COMMIT;

--====================================================
-- 4. Revoke All Privileges
--====================================================
REVOKE CREATE SESSION FROM YOUR_NAME;

REVOKE CREATE TABLE FROM YOUR_NAME;

REVOKE INSERT, SELECT, UPDATE, DELETE ON HR.STUDENT FROM YOUR_NAME;