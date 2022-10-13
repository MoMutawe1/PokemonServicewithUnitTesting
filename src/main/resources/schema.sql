--CREATE SCHEMA IF NOT EXISTS Pokemon;
--DROP TABLE IF EXISTS pokemon_tbl;

Create table pokemon_tbl (
    pokemon_id int NOT NULL primary key,
    pokemon_attack int,
    pokemon_strength int,
    pokemon_type varchar(255),
    pokemon_position int
);