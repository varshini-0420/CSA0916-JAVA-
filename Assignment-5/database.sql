CREATE DATABASE IF NOT EXISTS csa09_b2_testdb;

USE csa09_b2_testdb;

CREATE TABLE IF NOT EXISTS bus_pass (
    pass_id INT PRIMARY KEY,
    passenger_name VARCHAR(100),
    route VARCHAR(100),
    amount DOUBLE
);

-- Verify table structure
DESC bus_pass;

-- Display migrated records
SELECT * FROM bus_pass;

-- Count total records
SELECT COUNT(*) AS Total_Records FROM bus_pass;