DROP DATABASE hib_demo;
CREATE DATABASE hib_demo DEFAULT CHARACTER SET utf8;
jiaochengjiaochenghib_demo

-- 建表
CREATE TABLE users(
 id INT PRIMARY KEY AUTO_INCREMENT,
 NAME VARCHAR(20),
 age INT
);


DESC t_idCard;
DESC t_user;