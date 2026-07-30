/* ============================================================
   1. Create Player Table
   id name age
   - id NOT NULL
   - id UNIQUE
   - name UNIQUE
   ============================================================ */

CREATE TABLE Player (
    id INT NOT NULL,
    name VARCHAR(100),
    age INT,
    CONSTRAINT uq_player_id UNIQUE (id),
    CONSTRAINT uq_player_name UNIQUE (name)
);


/* ============================================================
   2. Create Manager Table
   id name salary
   - id NOT NULL
   - id and name UNIQUE together
   ============================================================ */

CREATE TABLE Manager (
    id INT NOT NULL,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    CONSTRAINT uq_manager_id_name UNIQUE (id, name)
);


/* ============================================================
   3. Create Manager Table
   id name age
   - id NOT NULL
   - id UNIQUE (PRIMARY KEY)
   ============================================================ */

CREATE TABLE Manager (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT
);