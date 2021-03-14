DROP TABLE IF EXISTS subscribers;

CREATE TABLE subscribers (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL
);

INSERT INTO subscribers (first_name, last_name) VALUES
  ('Zsolt', 'Horvath',"fiktivemail@fiktiv.hu"),
  ('Jakab', 'Gipsz',"fiktivemail@fiktiv.hu");