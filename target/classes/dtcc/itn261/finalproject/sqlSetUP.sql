/********************************************************
* This script creates the database named information_form_schema
*********************************************************/
DROP DATABASE IF EXISTS information_form_schema;
CREATE DATABASE IF NOT EXISTS information_form_schema;
USE information_form_schema;
CREATE TABLE IF NOT EXISTS personal_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    phoneNumber VARCHAR(15) UNIQUE,
    birthday DATE,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    zipCode INT,
    notes TEXT,
    CONSTRAINT chk_phone_number CHECK (phoneNumber REGEXP '^\\d{10}$|^((\\d{3}-){2}\\d{4})$|^\\(\\d{3}\\) \\d{3}-\\d{4}$|^\\d{3}-\\d{7}$'
        OR phoneNumber IS NULL),
    CONSTRAINT chk_email_format CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$')
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_UNICODE_CI;
INSERT INTO personal_info (firstName, lastName, phoneNumber, birthday, email, address, city, state, zipCode, notes) VALUES
('John', 'Doe', '123-4567890', '1990-05-15', 'john.doe@example.com', '1234 Elm St', 'Anytown', 'CA', 90210, 'No additional notes.'),
('Jane', 'Smith', '987-6543210', '1988-07-20', 'jane.smith@example.com', '5678 Oak St', 'Othertown', 'TX', 75001, 'Prefers email contact.'),
('Alice', 'Johnson', '555-1234567', '1992-03-05', 'alice.johnson@example.com', '910 Pine Ave', 'Smallville', 'NY', 10001, 'Interested in newsletters.'),
('Bob', 'Brown', '444-7654321', '1985-09-15', 'bob.brown@example.com', '135 Maple Dr', 'Bigtown', 'FL', 33101, 'No additional notes.'),
('Carol', 'Davis', '333-6789012', '1991-12-10', 'carol.davis@example.com', '246 Spruce Way', 'Laketown', 'NV', 89501, 'Allergic to peanuts.'),
('David', 'Wilson', '222-9876543', '1984-11-20', 'david.wilson@example.com', '579 Birch Pl', 'Hilltown', 'NC', 28201, 'Volunteer.'),
('Eve', 'Moore', '111-2468013', '1993-01-30', 'eve.moore@example.com', '802 Cedar Ln', 'Rivertown', 'WA', 98101, 'Frequent traveler.'),
('Frank', 'Taylor', '666-1357902', '1979-08-25', 'frank.taylor@example.com', '425 Elmwood St', 'Beachtown', 'OR', 97001, 'Interested in tech updates.'),
('Grace', 'Anderson', '777-2468135', '1975-06-07', 'grace.anderson@example.com', '548 Pinecone Rd', 'Mountainview', 'CO', 80201, 'Crafting enthusiast.'),
('Henry', 'Thomas', '888-9753102', '1995-04-12', 'henry.thomas@example.com', '367 Oakwood Ave', 'Valleytown', 'PA', 19101, 'Gamer and tech lover.');
