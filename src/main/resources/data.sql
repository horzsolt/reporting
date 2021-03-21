DROP TABLE IF EXISTS subscribers;

CREATE TABLE subscriber (
  id INT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);

INSERT INTO subscriber (id, first_name, last_name, email) VALUES
  (1, 'Zsolt', 'Horvath','fiktivemail@fiktiv.hu'),
  (2, 'Jakab', 'Gipsz','fiktivemail1@fiktiv.hu');