CREATE SCHEMA IF NOT EXISTS Pokemon;
Create table pokemon_tbl (
    pokemon_id int NOT NULL primary key,
    pokemon_attack int,
    pokemon_strength int,
    pokemon_type varchar(255),
    pokemon_position int
);
insert into pokemon_tbl (pokemon_id, pokemon_attack, pokemon_strength, pokemon_type, pokemon_position) values (1, 900, 900,'light',1), (2, 900, 900,'dark',1);
