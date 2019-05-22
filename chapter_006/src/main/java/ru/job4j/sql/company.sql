CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company
VALUES (1, 'Zaporozhec'),
       (2, 'Apple'),
       (3, 'GElectric'),
       (4, 'AutoVaz'),
       (5, 'Google');


INSERT INTO person
VALUES (1, 'Vasily', 5),
       (2, 'Smith', 4),
       (3, 'Mikhail', 3),
       (4, 'Uasya', 2),
       (5, 'Artem', 1),
       (6, 'IngeBorgeDaptunaite', 5),
       (7, 'Bond, James Bond', 4);

--1) Retrieve in a single query:
-- names of all persons that are NOT in the company with id = 5
--  company name for each person

SELECT p.name, c.name
FROM person AS p
         JOIN company AS c 
		 ON p.company_id = c.id
		 WHERE c.id <> 5
		 ORDER BY c.name;
		 
--2) Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, COUNT(p.company_id) AS peoples
FROM company AS c
JOIN person AS p ON c.id = p.company_id
GROUP BY c.name 
order by peoples DESC LIMIT 1;
