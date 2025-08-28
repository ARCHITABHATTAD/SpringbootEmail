
-- Create database
CREATE DATABASE IF NOT EXISTS otpdb;

-- Use the database
USE otpdb;

-- Create table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    mobile VARCHAR(20) NOT NULL,
    otp VARCHAR(10),
    otp_generated_time BIGINT
);
