DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
  id long AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email_address VARCHAR(250) NOT NULL
);

INSERT INTO employees (first_name, last_name, email_address) VALUES
  ('Adi', 'Dangote', 'adi@gmail.com'),
  ('Ram', 'sita', 'ram@gmail.com'),
  ('Vikas', 'Alakija', 'vikas@gmail.com');