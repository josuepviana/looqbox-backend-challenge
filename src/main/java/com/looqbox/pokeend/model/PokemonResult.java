package com.looqbox.pokeend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PokemonResult implements Serializable {
    private final String name;
    private final Integer start;
    private final Integer end;

    // Builds the output object including start and end of queried string occurrence
    public static List<PokemonResult> fromPokemonList(List<PokemonResponse> pokemons, String query) {
        return pokemons.stream()
                .map(pokemon -> {
                    // pikachu pi 0 1
                    int start = pokemon.getName().indexOf(query);
                    int end = start + query.length() - 1;

                    return new PokemonResult(pokemon.getName(), start, end);
                })
                .collect(Collectors.toList());
    }

}
