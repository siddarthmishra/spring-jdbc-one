CREATE DATABASE  IF NOT EXISTS `spring_jdbc`;

USE `spring_jdbc`;

CREATE USER 'springjdbc'@'localhost' IDENTIFIED BY 'springjdbc';

GRANT ALL PRIVILEGES ON * . * TO 'springjdbc'@'localhost';

ALTER USER 'springjdbc'@'localhost' IDENTIFIED WITH mysql_native_password BY 'springjdbc';

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into student values('1', 'Siddarth', 'Mishra', 'siddarthmishra@yahoo.com');