DROP TABLE IF EXISTS subscribers;

CREATE TABLE subscriber (
  id INT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);
