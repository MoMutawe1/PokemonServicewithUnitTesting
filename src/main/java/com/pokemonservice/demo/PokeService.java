package com.pokemonservice.demo;

import com.pokemonservice.demo.controller.AddResponse;
import com.pokemonservice.demo.model.Pokemon;
import com.pokemonservice.demo.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Service
public class PokeService {

    @Autowired
    PokemonRepository repo;

    static HashMap<Integer, Pokemon> pokemonCategories;

    public List<Pokemon> getAllPokes(){
        return repo.findAll();
    }

    public Pokemon getPokeById(int id){
        return repo.findById(id).get();
    }

    public Pokemon getPokeByType(String pokeType){
        List<Pokemon> allPoke = repo.findAll();
        Pokemon pokemon = null;
        for(Pokemon poke: allPoke){
            if(poke.getType().equalsIgnoreCase(pokeType))
                pokemon = poke;
        }
        return pokemon;
    }

    public Pokemon updatePokemon(Pokemon pokemon){
        repo.save(pokemon);
        return pokemon;
    }

    public Pokemon addNewPokemon(Pokemon pokemon){
        pokemon.setPokemonId(getMaxId());
        repo.save(pokemon);
        return pokemon;
    }

    public int getMaxId(){
        return repo.findAll().size()+1;
    }

    public AddResponse deletePokemon(int id){
        repo.deleteById(id);
        AddResponse response = new AddResponse();
        response.setMsg("Record Deleted..");
        response.setId(id);
        return response;
    }

    public void deletePokemon(Pokemon pokemon){
        repo.delete(pokemon);
    }
}
