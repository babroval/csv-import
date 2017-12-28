DROP SCHEMA IF EXISTS `users_db`;

CREATE SCHEMA IF NOT EXISTS `users_db`;

USE `users_db`;

CREATE TABLE `users` (
  `id`          INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name`   	    VARCHAR(255) DEFAULT "",
  `surname`    	VARCHAR(255) DEFAULT "", 
  `login`   	VARCHAR(255) DEFAULT "",
  `email`    	VARCHAR(255) DEFAULT "",
  `tel`	        VARCHAR(255) DEFAULT ""
 );
