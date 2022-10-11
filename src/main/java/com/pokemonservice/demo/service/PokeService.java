package com.pokemonservice.demo.service;

import com.pokemonservice.demo.controller.AddResponse;
import com.pokemonservice.demo.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class PokeService {
    static HashMap<Integer, Pokemon> pokemonCategories;

    public PokeService() {
        pokemonCategories = new HashMap<Integer, Pokemon>();
        Pokemon electric = new Pokemon(1, 800, 400, "electric", 1);
        Pokemon water = new Pokemon(2, 400, 400, "water", 5 );
        Pokemon fire = new Pokemon(3, 1000, 300, "fire", 2 );
        Pokemon earth = new Pokemon(4, 500, 800, "earth", 3);
        Pokemon air = new Pokemon(5, 500, 500, "air", 4);
        Pokemon notFound = new Pokemon(-1, -1, -1, "Type Not Found", -1);

        pokemonCategories.put(1, electric);
        pokemonCategories.put(2, water);
        pokemonCategories.put(3, fire);
        pokemonCategories.put(4, earth);
        pokemonCategories.put(5, air);
    }

    public List getAllPokes(){
        List pokeList = new ArrayList(pokemonCategories.values());
        return pokeList;
    }

    public Pokemon getPokeById(int pokeId){
        Pokemon pokemon = pokemonCategories.get(pokeId);
        return pokemon;
    }

    public Pokemon getPokeByType(String pokeType){
        Pokemon pokemon = null;

            for (int i : pokemonCategories.keySet()) {
                if (pokemonCategories.get(i).getType().equals(pokeType))
                    pokemon = pokemonCategories.get(i);
            }
        return pokemon;
    }

    public Pokemon updatePokemon(Pokemon pokemon){
        if(pokemon.getPokemonId()>0)
            pokemonCategories.put(pokemon.getPokemonId(), pokemon);
        return pokemon;
    }

    public Pokemon addNewPokemon(Pokemon pokemon){
        pokemon.setPokemonId(getMaxId());
        pokemonCategories.put(pokemon.getPokemonId(), pokemon);
        return pokemon;
    }

    public static int getMaxId(){
        int max=0;
        for(int id: pokemonCategories.keySet())
            if(max<id)
                max=id;
        return max+1;
    }

    public AddResponse deletePokemon(int id){
        pokemonCategories.remove(id);
        AddResponse response = new AddResponse();
        response.setId(id);
        response.setMsg("record deleted.");
        return response;
    }
}
