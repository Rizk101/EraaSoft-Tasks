-- Q1
SELECT NAME, MARKS,
       DECODE(
           SIGN(MARKS - 90), 1, 'A',
           SIGN(MARKS - 80), 1, 'B',
           SIGN(MARKS - 70), 1, 'C',
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
       DECODE(STATUS,
           'P', 'Pending',
           'S', 'Shipped',
           'D', 'Delivered'
       ) AS FULL_STATUS
FROM ORDERS;

COMMIT;