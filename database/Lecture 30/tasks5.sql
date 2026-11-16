-- Q1
SELECT NAME, MARKS,
       DECODE(
           TRUNC(MARKS / 10),
           10, 'A',
           9,  'A',
           8,  'B',
           7,  'C',
           'F'
       ) AS GRADE
FROM STUDENTS;

-- Q2
CREATE TABLE ORDERS (
    STATUS CHAR(1)
);

INSERT INTO ORDERS VALUES ('P');
INSERT INTO ORDERS VALUES ('S');
INSERT INTO ORDERS VALUES ('D');

-- Q3
SELECT STATUS,
       DECODE(
           STATUS,
           'P', 'Pending',
           'S', 'Shipped',
           'D', 'Delivered'
       ) AS FULL_STATUS
FROM ORDERS;

COMMIT;