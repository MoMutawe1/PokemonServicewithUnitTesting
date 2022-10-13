package com.pokemonservice.demo.controller;

import com.pokemonservice.demo.PokeService;
import com.pokemonservice.demo.model.Pokemon;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {TrainersControllerTest.class})
public class TrainersControllerTest {

    @Mock
    PokeService pokemonService;

    @InjectMocks
    TrainersController tController;

    List<Pokemon> myPokemonsList;

    @Test
    @Order(1)
    public void getPokeTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        when(pokemonService.getAllPokes()).thenReturn(myPokemonsList);
        ResponseEntity<List<Pokemon>> pokemonsList = tController.getAllPokemons();
        assertEquals(HttpStatus.FOUND, pokemonsList.getStatusCode());
        assertEquals(3, pokemonsList.getBody().size());
    }

    @Test
    @Order(2)
    public void getPokeByIdTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));
        int pokemonId = 0;

        when(pokemonService.getPokeById(pokemonId)).thenReturn(myPokemonsList.get(pokemonId));
        ResponseEntity<Pokemon> response = tController.getPokeById(pokemonId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1,response.getBody().getPokemonId());
    }

    @Test
    @Order(3)
    public void getPokeByTypeTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));
        String pokemonType = "water";

        when(pokemonService.getPokeByType(pokemonType)).thenReturn(myPokemonsList.get(0));
        ResponseEntity<Pokemon> response = tController.getPokeByType(pokemonType);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("water",response.getBody().getType());
    }

    @Test
    @Order(4)
    public void addPokemonTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        Pokemon newPokemon = (new Pokemon(4, 400, 400, "air", 4));
        when(pokemonService.addNewPokemon(newPokemon)).thenReturn(newPokemon);
        assertEquals(newPokemon,tController.addPokemon(newPokemon));
    }

    @Test
    @Order(5)
    public void updatePokemonTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));
        int pokemonId = 2;
        Pokemon newPokemon = new Pokemon(2, 800, 400, "fire", 2);

        when(pokemonService.getPokeById(pokemonId)).thenReturn(newPokemon);
        when(pokemonService.updatePokemon(newPokemon)).thenReturn(newPokemon);
        ResponseEntity<Pokemon> response = tController.updatePokemon(pokemonId,newPokemon);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("fire", response.getBody().getType());
        assertEquals(2, response.getBody().getPokemonId());
    }

    @Test
    @Order(6)
    public void deletePokemonTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        Pokemon pokemon = new Pokemon(3, 500, 500, "earth", 3);
        int pokemonId = 3;
        AddResponse response = new AddResponse();
        response.setId(3);
        response.setMsg("deleted successfully");
        when(pokemonService.deletePokemon(pokemonId)).thenReturn(response);
        assertEquals(HttpStatus.OK, tController.deletePokemon(pokemonId).getStatusCode());
    }
}
