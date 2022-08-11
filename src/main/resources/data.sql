--CREATE TABLE employee (
--  id INTEGER NOT NULL AUTO_INCREMENT,
--  employee_name varchar(45),
--  salary varchar(45) NOT NULL,
--  created_at datetime NOT NULL,
--  updated_at datetime DEFAULT NULL,
--  PRIMARY KEY (id)
--);
--
--INSERT INTO employee (employee_name, salary, created_at, updated_at)
--VALUES ('Steve', '50000', '2022-04-04 11:33:30', NULL);
--
--INSERT INTO employee (employee_name, salary, created_at, updated_at)
--VALUES ('Bill', '55000', '2022-04-05 12:33:30', NULL);
--
--INSERT INTO employee (employee_name, salary, created_at, updated_at)
--VALUES ('Mark', '30000', '2022-04-01 04:31:50', '2022-04-08 09:12:32');
--
--INSERT INTO employee (employee_name, salary, created_at, updated_at)
--VALUES ('Josh', '60000', '2022-04-03 09:22:25', '2022-04-07 12:34:54');

-----------------------------BOOKS---------------------------------

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (100, 'author_java', 'desc_java', 'java', 100, 'TECHNOLOGY');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (101, 'author_c++', 'desc_c++', 'c++', 200, 'TECHNOLOGY');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (102, 'author_python', 'desc_python', 'python', 300, 'TECHNOLOGY');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (103, 'author_react.js', 'desc_react.js', 'react.js', 400, 'TECHNOLOGY');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (104, 'author_avenger', 'desc_avenger', 'avenger', 120, 'ACTION');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (105, 'author_fantastic4', 'desc_fantastic4', 'fantastic4', 130, 'ACTION');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (106, 'author_blood_syndicate', 'desc_blood_syndicate', 'blood syndicate', 150, 'COMIC');

INSERT INTO books (isbn, author, description, name, price, type)
VALUES (107, 'author_axe', 'desc_axe', 'axe', 150, 'COMIC');

-----------------------------DISCOUNT---------------------------------

INSERT INTO discounts (id, active, discount_type, value)
VALUES (200, true, 'TECHNOLOGY', 10);

INSERT INTO discounts (id, active, discount_type, value)
VALUES (201, true, 'ACTION', 5);

INSERT INTO discounts (id, active, discount_type, value)
VALUES (202, true, 'COMIC', 20);

INSERT INTO promocodes (id, active, code, discount_applicable_amount, flat_discount)
VALUES (301, true, 'flat100', 500, 100);

INSERT INTO promocodes (id, active, code, discount_applicable_amount, flat_discount)
VALUES (302, true, 'flat200', 1000, 200);

INSERT INTO promocodes (id, active, code, discount_applicable_amount, flat_discount)
VALUES (303, false, 'flat300', 2000, 300);


