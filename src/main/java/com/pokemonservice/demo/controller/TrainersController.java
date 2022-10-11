package com.pokemonservice.demo.controller;

import com.pokemonservice.demo.model.Pokemon;
import com.pokemonservice.demo.service.PokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Pokemon> getPokeById(@PathVariable(value = "id") int id){
        try {
            Pokemon pokemon = pokeService.getPokeById(id);
            return new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getpokemon/pokemontype")
    public ResponseEntity<Pokemon> getPokeByType(@RequestParam(value = "type") String pokemonType){
        try {
            Pokemon pokemon = pokeService.getPokeByType(pokemonType);
            return new ResponseEntity<Pokemon>(pokemon, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addPokemon")
    public Pokemon addPokemon(@RequestBody Pokemon newPokemon){
        return pokeService.addNewPokemon(newPokemon);
    }

    @PutMapping("/updatepokemon/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable(value = "id") int id, @RequestBody Pokemon pokemon){
        try {
            Pokemon existPokemon = pokeService.getPokeById(id);

            existPokemon.setType(pokemon.getType());
            existPokemon.setAttack(pokemon.getAttack());
            existPokemon.setStrength(pokemon.getStrength());
            existPokemon.setPosition(pokemon.getPosition());

            Pokemon updatedPokemon = pokeService.updatePokemon(existPokemon);
            return new ResponseEntity<Pokemon>(updatedPokemon, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/deletepokemon/{id}")
    public ResponseEntity<AddResponse> deletePokemone(@PathVariable (value = "id") int id){
        try {
            AddResponse pokemon = pokeService.deletePokemon(id);
            return new ResponseEntity<AddResponse>(pokemon, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
