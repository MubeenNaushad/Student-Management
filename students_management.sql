create database students_management;

use students_management;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `admin` VALUES (1,'admin','123');

CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `date_of_birth` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `father_name` varchar(150) NOT NULL,
  `mother_name` varchar(150) NOT NULL,
  `address1` text NOT NULL,
  `address2` text NOT NULL,
  `image_path` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
); 


CREATE TABLE `course` (
  `id` int NOT NULL,
  `student_id` int DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `course1` varchar(200) DEFAULT NULL,
  `course2` varchar(200) DEFAULT NULL,
  `course3` varchar(200) DEFAULT NULL,
  `course4` varchar(200) DEFAULT NULL,
  `course5` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_id` (`student_id`),
  CONSTRAINT `fk_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `score` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `semester` int NOT NULL,
  `course1` varchar(200) NOT NULL,
  `score1` double NOT NULL,
  `course2` varchar(200) NOT NULL,
  `score2` double NOT NULL,
  `course3` varchar(200) NOT NULL,
  `score3` double NOT NULL,
  `course4` varchar(200) NOT NULL,
  `score4` double NOT NULL,
  `course5` varchar(200) NOT NULL,
  `score5` double NOT NULL,
  `average` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stu_id` (`student_id`),
  CONSTRAINT `fk_stu_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);