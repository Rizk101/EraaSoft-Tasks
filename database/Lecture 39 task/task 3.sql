SELECT *
FROM departments
WHERE department_id IN (
    SELECT department_id
    FROM departments
    WHERE department_name IN (
        'Administration',
        'Marketing',
        'Purchasing',
        'Human Resources',
        'Shipping'
    )
);