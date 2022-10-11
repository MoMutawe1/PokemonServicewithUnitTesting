package com.pokemonservice.demo.repositories;

import com.pokemonservice.demo.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
