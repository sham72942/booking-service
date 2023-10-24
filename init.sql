-- Create the database
CREATE DATABASE bookingsdb;

\c bookingsdb;

-- Create a user and grant privileges
CREATE USER docker WITH PASSWORD 'docker';
GRANT ALL PRIVILEGES ON DATABASE bookingsdb TO docker;
GRANT USAGE, CREATE ON SCHEMA public TO docker;
