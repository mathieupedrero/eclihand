-- CREATE OBJECTS INSTRUCTIONS
SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

ALTER SCHEMA public OWNER TO eclihand;
COMMENT ON SCHEMA public IS 'standard public schema';

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA pg_catalog;

SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;


commit;