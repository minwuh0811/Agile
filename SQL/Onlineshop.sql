SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';

DROP DATABASE Onlineshop;
CREATE DATABASE IF NOT EXISTS Onlineshop;
USE Onlineshop;

CREATE TABLE IF NOT EXISTS VaruLager(
	ID TINYINT UNSIGNED NOT NULL,
    varorName VARCHAR(10) NOT NULL,
    varorPrice DOUBLE UNSIGNED NOT NULL,
    typ VARCHAR(10) NOT NULL,
    varorKategori VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
CREATE TABLE IF NOT EXISTS Admin(
	staffNumberID TINYINT UNSIGNED NOT NULL,
    administratorFirstName VARCHAR(10) NOT NULL,
    administratorLastName varchar(10) NOT NULL,
    administratorMail varchar(20) not null,
    loginName varchar(20) not null,
    loginPassword varchar(20) not null,    
    PRIMARY KEY (staffNumberID)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
    
CREATE TABLE IF NOT EXISTS User(	
    firstName VARCHAR(10) NOT NULL,
    lastname varchar(20) NOT NULL,
    mail varchar(20) NOT NULL,
    ID TINYINT UNSIGNED NOT NULL,
    userName VARCHAR(10) NOT NULL,admin
    userPassword VARCHAR(10) NOT NULL,
    PRIMARY KEY (ID)
    )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;