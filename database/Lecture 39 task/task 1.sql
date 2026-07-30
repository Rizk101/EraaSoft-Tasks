-- 1. NATURAL JOIN
SELECT location_id,
       street_address,
       city,
       country_name
FROM locations
NATURAL JOIN countries;
-- 2. JOIN USING (COUNTRY_ID)

SELECT location_id,
       street_address,
       city,
       country_name
FROM locations
JOIN countries
USING (country_id);
-- 3. JOIN ON (COUNTRY_ID)

SELECT l.location_id,
       l.street_address,
       l.city,
       c.country_name
FROM locations l
JOIN countries c
ON l.country_id = c.country_id;

-- 4. INNER JOIN
```sql
SELECT l.location_id,
       l.street_address,
       l.city,
       c.country_name
FROM locations l
INNER JOIN countries c
ON l.country_id = c.country_id;

-- 5. LEFT OUTER JOIN
SELECT l.location_id,
       l.street_address,
       l.city,
       c.country_name
FROM locations l
LEFT OUTER JOIN countries c
ON l.country_id = c.country_id;

-- 6. RIGHT OUTER JOIN
SELECT l.location_id,
       l.street_address,
       l.city,
       c.country_name
FROM locations l
RIGHT OUTER JOIN countries c
ON l.country_id = c.country_id;

-- 7. FULL OUTER JOIN
SELECT l.location_id,
       l.street_address,
       l.city,
       c.country_name
FROM locations l
FULL OUTER JOIN countries c
ON l.country_id = c.country_id;