package com.pokemonservice.demo.controller;

import com.pokemonservice.demo.model.Pokemon;
import com.pokemonservice.demo.service.PokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrainersController {

    @Autowired
    PokeService pokeService;

    @GetMapping("/getallpokemons")
    public List getPoke(){
        return pokeService.getAllPokes();
    }

    @GetMapping("/getpokemon/{id}")
    public Pokemon getPokeById(@PathVariable(value = "id") int id){
        return pokeService.getPokeById(id);
    }

    @GetMapping("/getpokemon/pokemontype")
    public Pokemon getPokeByType(@RequestParam(value = "type") String pokemonType){
        return pokeService.getPokeByType(pokemonType);
    }

    @PostMapping("/addPokemon")
    public Pokemon addPokemon(@RequestBody Pokemon newPokemon){
        return pokeService.addNewPokemon(newPokemon);
    }

    @PutMapping("/updatepokemon")
    public Pokemon updatePokemon(@RequestBody Pokemon updatePokemon){
        return pokeService.updatePokemon(updatePokemon);
    }

    @DeleteMapping("/deletepokemon/{id}")
    public AddResponse deletePokemone(@PathVariable (value = "id") int id){
        return pokeService.deletePokemon(id);
    }

}
