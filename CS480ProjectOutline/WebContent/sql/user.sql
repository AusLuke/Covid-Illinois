CREATE DATABASE covid_illinois;

USE covid_illinois;

CREATE TABLE tb_user (
username VARCHAR(50) NOT NULL PRIMARY KEY,
password VARCHAR(50),
email VARCHAR(50)
);