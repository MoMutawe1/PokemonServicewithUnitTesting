package com.pokemonservice.demo.controller;

import com.pokemonservice.demo.model.Pokemon;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTest {

    @Test
    @Order(1)
    void getAllPokemonsIntegrationTest() throws JSONException {
        String expected = "[\n" +
                "    {\n" +
                "        \"position\": 1,\n" +
                "        \"type\": \"light\",\n" +
                "        \"pokemonId\": 1,\n" +
                "        \"strength\": 900,\n" +
                "        \"attack\": 900\n" +
                "    },\n" +
                "    {\n" +
                "        \"position\": 1,\n" +
                "        \"type\": \"dark\",\n" +
                "        \"pokemonId\": 2,\n" +
                "        \"strength\": 900,\n" +
                "        \"attack\": 900\n" +
                "    }\n" +
                "]";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity response = restTemplate.getForEntity("http://localhost:8080/getallpokemons", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, (String) response.getBody(),false);
    }

    @Test
    @Order(2)
    void getPokemonByIdIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"position\": 1,\n" +
                "    \"type\": \"light\",\n" +
                "    \"pokemonId\": 1,\n" +
                "    \"strength\": 900,\n" +
                "    \"attack\": 900\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getpokemon/1", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    @Order(3)
    void getPokemonByTypeIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"position\": 1,\n" +
                "    \"type\": \"light\",\n" +
                "    \"pokemonId\": 1,\n" +
                "    \"strength\": 900,\n" +
                "    \"attack\": 900\n" +
                "}";

        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getpokemon/pokemontype?type=light", String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    @Order(4)
    void addPokemonIntegrationTest() throws JSONException {
        Pokemon pokemon = new Pokemon(3, 1000, 1500, "magma", 3);

        String expected = "{\n" +
                "    \"position\": 3,\n" +
                "    \"type\": \"magma\",\n" +
                "    \"pokemonId\": 3,\n" +
                "    \"strength\": 1500,\n" +
                "    \"attack\": 1000\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Pokemon> request = new HttpEntity<Pokemon>(pokemon,header);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addPokemon", request,String.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    @Order(5)
    void updatePokemonIntegrationTest() throws JSONException {
        Pokemon pokemon = new Pokemon(3, 1200, 1500, "magma", 2);

        String expected = "{\n" +
                "    \"position\": 2,\n" +
                "    \"type\": \"magma\",\n" +
                "    \"pokemonId\": 3,\n" +
                "    \"strength\": 1500,\n" +
                "    \"attack\": 1200\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Pokemon> request = new HttpEntity<Pokemon>(pokemon,header);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/updatepokemon/3", HttpMethod.PUT, request,String.class);

        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(),false);
    }

    @Test
    @Order(6)
    void deletePokemonIntegrationTest() throws JSONException {
        Pokemon pokemon = new Pokemon(3, 1200, 1500, "magma", 2);

        String expected = "{\n" +
                "    \"position\": 2,\n" +
                "    \"type\": \"magma\",\n" +
                "    \"pokemonId\": 3,\n" +
                "    \"strength\": 1500,\n" +
                "    \"attack\": 1200\n" +
                "}";
        //restTemplate.delete("http://localhost:8080/deletepokemon/3");
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Pokemon> request = new HttpEntity<Pokemon>(pokemon,header);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/deletepokemon/3", HttpMethod.DELETE, request,String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        //JSONAssert.assertEquals(expected, response.getBody(),false);
        System.out.println(response.getBody());
    }
}
