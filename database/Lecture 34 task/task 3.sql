/* ===========================
   Language Table
   =========================== */

CREATE TABLE Language (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

/* ===========================
   Teacher Table
   =========================== */

CREATE TABLE Teacher (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    language_id INT,

    CONSTRAINT fk_teacher_language
        FOREIGN KEY (language_id)
        REFERENCES Language(id)
);