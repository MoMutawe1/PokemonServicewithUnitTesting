package com.pokemonservice.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Pokemon", schema = "Pokemon")
public class Pokemon {

    @Id
    @Column(name = "pokemon_id")
    private int pokemon_id;

    @Column(name = "pokemon_attack")
    private int pokemon_attack;

    @Column(name = "pokemon_strength")
    private int pokemon_strength;

    @Column(name = "pokemon_type")
    private String pokemon_type;

    @Column(name = "pokemon_position")
    private int pokemon_position;

    public Pokemon(){}
    public Pokemon(int pokemonId, int pokemon_attack, int strength, String type, int position) {
        this.pokemon_id = pokemonId;
        this.pokemon_attack = pokemon_attack;
        this.pokemon_strength = strength;
        this.pokemon_type = type;
        this.pokemon_position = position;
    }

    public int getPokemonId() {
        return pokemon_id;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemon_id = pokemonId;
    }

    public int getAttack() {
        return pokemon_attack;
    }

    public void setAttack(int attack) {
        this.pokemon_attack = attack;
    }

    public int getStrength() {
        return pokemon_strength;
    }

    public void setStrength(int strength) {
        this.pokemon_strength = strength;
    }

    public String getType() {
        return pokemon_type;
    }

    public void setType(String type) {
        this.pokemon_type = type;
    }

    public int getPosition() {
        return pokemon_position;
    }

    public void setPosition(int position) {
        this.pokemon_position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return pokemon_id == pokemon.pokemon_id && pokemon_attack == pokemon.pokemon_attack && pokemon_strength == pokemon.pokemon_strength && pokemon_type.equals(pokemon.pokemon_type) && pokemon_position == pokemon.pokemon_position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemon_id, pokemon_attack, pokemon_strength, pokemon_type, pokemon_position);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemon_id +
                ", attack=" + pokemon_attack +
                ", strength=" + pokemon_strength +
                ", type='" + pokemon_type + '\'' +
                ", position='" + pokemon_position + '\'' +
                '}';
    }
}
