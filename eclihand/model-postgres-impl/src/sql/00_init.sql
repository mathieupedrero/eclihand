-- DROP/CREATE DB INSTRUCTIONS
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE eclihand;

CREATE DATABASE eclihand WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_France.1252' LC_CTYPE = 'French_France.1252';
ALTER DATABASE eclihand OWNER TO postgres;