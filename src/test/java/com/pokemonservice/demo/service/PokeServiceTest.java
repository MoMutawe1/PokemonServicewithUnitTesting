package com.pokemonservice.demo.service;

import com.pokemonservice.demo.PokeService;
import com.pokemonservice.demo.controller.AddResponse;
import com.pokemonservice.demo.model.Pokemon;
import com.pokemonservice.demo.repositories.PokemonRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes={PokeServiceTest.class})
public class PokeServiceTest {

    @Mock
    PokemonRepository pokemonRepository;

    @InjectMocks
    PokeService pokemonService;

    public List<Pokemon> myPokemonsList;

    @Test
    @Order(1)
    public void getAllPokesTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        when(pokemonRepository.findAll()).thenReturn(myPokemonsList); // Mocking
        assertEquals(3,pokemonService.getAllPokes().size());
    }

    @Test
    @Order(2)
    public void getPokeByIdTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        int pokemonId = 1;
        when(pokemonRepository.findById(pokemonId)).thenReturn(java.util.Optional.ofNullable(myPokemonsList.get(0))); // Mocking

        assertEquals(1,pokemonService.getPokeById(pokemonId).getPokemonId());
    }

    @Test
    @Order(3)
    public void getPokeByTypeTest(){
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        when(pokemonRepository.findAll()).thenReturn(myPokemonsList); // Mocking

        String pokemonType = "water";
        assertEquals("water",pokemonService.getPokeByType(pokemonType).getType());
    }

    @Test
    @Order(4)
    public void addNewPokemonTest(){
        Pokemon pokemon = new Pokemon(1, 400, 400, "air", 4);

        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonService.addNewPokemon(pokemon));
    }

    @Test
    @Order(5)
    public void updatePokemonTest(){
        Pokemon pokemon = new Pokemon(1, 400, 400, "air", 4);

        when(pokemonRepository.save(pokemon)).thenReturn(pokemon);
        assertEquals(pokemon, pokemonService.updatePokemon(pokemon));
    }

    @Test
    @Order(6)
    public void deletePokemonTest(){
        Pokemon pokemon = new Pokemon(1, 400, 400, "air", 4);
        pokemonService.deletePokemon(pokemon);
        verify(pokemonRepository,times(1)).delete(pokemon);


    }
}
