
DROP DATABASE course;
CREATE DATABASE course;

\c course

-- DROP TABLE IF EXISTS client;
-- DROP TABLE IF EXISTS master;
-- DROP TABLE IF EXISTS device_instance;
-- DROP TABLE IF EXISTS support_request;
-- DROP TABLE IF EXISTS diagnostics_request;


CREATE TABLE client(
   id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL UNIQUE
);

CREATE TABLE master(
   id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL UNIQUE
);

-- DROP TABLE IF EXISTS device_bunch;
-- CREATE TABLE device_bunch(
--    id serial PRIMARY KEY,
--    name VARCHAR (50),
--    count INTEGER,
-- );

-- DROP TABLE IF EXISTS device_description;
-- CREATE TABLE device_description(
--    id serial PRIMARY KEY,
--    description VARCHAR (50),
--    count INTEGER,
-- );


CREATE TABLE diagnostics_request(
   id serial PRIMARY KEY,
   master_id INTEGER REFERENCES master(id),
   name VARCHAR (50) NOT NULL UNIQUE,
   status VARCHAR (50)
);

CREATE TABLE support_request(
   id serial PRIMARY KEY,
   client_id INTEGER REFERENCES client(id),
   master_id INTEGER REFERENCES master(id),
   name VARCHAR (50) NOT NULL UNIQUE,
   description VARCHAR (50),
   status VARCHAR (50)
);

CREATE TABLE device_instance(
   id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL UNIQUE,
   client_id INTEGER REFERENCES client(id),
   diagnostics_request_id INTEGER REFERENCES diagnostics_request(id),
   device_id INTEGER
);


