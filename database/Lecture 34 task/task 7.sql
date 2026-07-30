/* ===========================
   Employee Table
   =========================== */

CREATE TABLE Employee (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);

/* ===========================
   Phone Table
   =========================== */

CREATE TABLE Phone (
    id INT PRIMARY KEY,
    phoneNumber VARCHAR(20),
    employee_id INT UNIQUE,

    CONSTRAINT fk_phone_employee
        FOREIGN KEY (employee_id)
        REFERENCES Employee(id)
);