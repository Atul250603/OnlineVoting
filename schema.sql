-- Database Schema
CREATE DATABASE voting_system;
USE voting_system;

CREATE TABLE voters (
    id INT PRIMARY KEY AUTO_INCREMENT,
    voter_id VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    has_voted BOOLEAN DEFAULT FALSE
);

CREATE TABLE parties (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    symbol VARCHAR(255) NOT NULL,
    vote_count INT DEFAULT 0
);

-- Sample data
INSERT INTO parties (name, symbol) VALUES
    ('Party A', 'party-a-symbol.png'),
    ('Party B', 'party-b-symbol.png'),
    ('Party C', 'party-c-symbol.png');

INSERT INTO voters (voter_id, name, password, has_voted) VALUES
    ('V001', 'John Doe', 'password123', false),
    ('V002', 'Jane Smith', 'password456', false),
    ('V003', 'Bob Johnson', 'password789', false);