package com.looqbox.pokeend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonPage {

    // This is what is brought back if the base URL is used
    private Integer count;
    private String next;
    private String previous;
    private List<PokemonResponse> results;
}
