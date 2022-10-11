package com.pokemonservice.demo.model;

import java.util.Objects;

public class Pokemon {
    private int pokemonId;
    private int attack;
    private int strength;
    private String type;
    private int position;

    public Pokemon(){}
    public Pokemon(int pokemonId, int attack, int strength, String type, int position) {
        this.pokemonId = pokemonId;
        this.attack = attack;
        this.strength = strength;
        this.type = type;
        this.position = position;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemonId == pokemon.pokemonId && attack == pokemon.attack && strength == pokemon.strength && type.equals(pokemon.type) && position == pokemon.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonId, attack, strength, type, position);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", attack=" + attack +
                ", strength=" + strength +
                ", type='" + type + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
