package com.pokemonservice.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonservice.demo.PokeService;
import com.pokemonservice.demo.model.Pokemon;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.pokemonservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMvcTest.class })
public class ControllerMockMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    PokeService pokemonService;

    @InjectMocks
    TrainersController trainersController;

    @BeforeEach
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(trainersController).build();
    }

    List<Pokemon> myPokemonsList;
    Pokemon pokemon;

    @Test
    @Order(1)
    public void getPokeTest() throws Exception {
        myPokemonsList = new ArrayList<Pokemon>();
        myPokemonsList.add(new Pokemon(1, 400, 400, "water", 5));
        myPokemonsList.add(new Pokemon(2, 800, 400, "fire", 2));
        myPokemonsList.add(new Pokemon(3, 500, 500, "earth", 3));

        when(pokemonService.getAllPokes()).thenReturn(myPokemonsList);
        this.mockMvc.perform(get("/getallpokemons"))
                .andExpect(status().isFound())
                .andDo(print());
    }

    @Test
    @Order(2)
    public void getPokeByIdTest() throws Exception {
        pokemon = new Pokemon(1, 400, 400, "water", 5);
        int pokemonId = 0;

        when(pokemonService.getPokeById(pokemonId)).thenReturn(pokemon);
        this.mockMvc.perform(get("/getpokemon/{id}",pokemonId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".pokemonId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".type").value("water"))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void getPokeByTypeTest() throws Exception {
        pokemon = new Pokemon(1, 400, 400, "water", 5);
        String pokemonType = "water";

        when(pokemonService.getPokeByType(pokemonType)).thenReturn(pokemon);
        this.mockMvc.perform(get("/getpokemon/pokemontype").param("type","water"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".pokemonId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".type").value("water"))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void addPokemonTest() throws Exception {
        pokemon = new Pokemon(1, 400, 400, "water", 5);
        when(pokemonService.addNewPokemon(pokemon)).thenReturn(pokemon);

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody=mapper.writeValueAsString(pokemon);
        this.mockMvc.perform(post("/addPokemon")
                .content(jsonbody)
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(5)
    public void updatePokemonTest() throws Exception {
        pokemon = new Pokemon(1, 400, 400, "water", 5);
        int pokemonId = 0;
        when(pokemonService.getPokeById(pokemonId)).thenReturn(pokemon);
        when(pokemonService.updatePokemon(pokemon)).thenReturn(pokemon);

        ObjectMapper mapper = new ObjectMapper();
        String jsonbody=mapper.writeValueAsString(pokemon);

        this.mockMvc.perform(put("/updatepokemon/{id}",pokemonId)
                        .content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".pokemonId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".type").value("water"))
                .andDo(print());
    }

    @Test
    @Order(6)
    public void deletePokemonTest() throws Exception {
        pokemon = new Pokemon(1, 400, 400, "water", 5);
        int pokemonId = 0;

        when(pokemonService.getPokeById(pokemonId)).thenReturn(pokemon);
        this.mockMvc.perform(delete("/deletepokemon/{id}",pokemonId))
                .andExpect(status().isOk());
    }
}
