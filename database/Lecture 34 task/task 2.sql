/* ===========================
   Doctor Table
   =========================== */

CREATE TABLE Doctor (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2)
);

/* ===========================
   Patient Table
   =========================== */

CREATE TABLE Patient (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);

/* ===========================
   Doctor-Patient Relation
   (Many-to-Many)
   =========================== */

CREATE TABLE Doctor_Patient (
    doctor_id INT,
    patient_id INT,

    PRIMARY KEY (doctor_id, patient_id),

    CONSTRAINT fk_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES Doctor(id),

    CONSTRAINT fk_patient
        FOREIGN KEY (patient_id)
        REFERENCES Patient(id)
);