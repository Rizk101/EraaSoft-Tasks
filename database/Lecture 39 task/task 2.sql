-- 1. NATURAL JOIN
SELECT employee_id,
       job_id,
       job_title,
       start_date,
       end_date
FROM job_history
NATURAL JOIN jobs;


-- 2. JOIN USING (JOB_ID)
SELECT employee_id,
       job_id,
       job_title,
       start_date,
       end_date
FROM job_history
JOIN jobs
USING (job_id);


-- 3. JOIN ON (JOB_ID)
SELECT jh.employee_id,
       jh.job_id,
       j.job_title,
       jh.start_date,
       jh.end_date
FROM job_history jh
JOIN jobs j
ON jh.job_id = j.job_id;


-- 4. INNER JOIN
SELECT jh.employee_id,
       jh.job_id,
       j.job_title,
       jh.start_date,
       jh.end_date
FROM job_history jh
INNER JOIN jobs j
ON jh.job_id = j.job_id;


-- 5. LEFT OUTER JOIN
SELECT jh.employee_id,
       jh.job_id,
       j.job_title,
       jh.start_date,
       jh.end_date
FROM job_history jh
LEFT OUTER JOIN jobs j
ON jh.job_id = j.job_id;


-- 6. RIGHT OUTER JOIN
SELECT jh.employee_id,
       jh.job_id,
       j.job_title,
       jh.start_date,
       jh.end_date
FROM job_history jh
RIGHT OUTER JOIN jobs j
ON jh.job_id = j.job_id;


-- 7. FULL OUTER JOIN
SELECT jh.employee_id,
       jh.job_id,
       j.job_title,
       jh.start_date,
       jh.end_date
FROM job_history jh
FULL OUTER JOIN jobs j
ON jh.job_id = j.job_id;