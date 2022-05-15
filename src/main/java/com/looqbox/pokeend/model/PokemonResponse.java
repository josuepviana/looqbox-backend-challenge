package com.looqbox.pokeend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonResponse{

    // Each pokemon in the base URL has a name and an URL with an especific ID to return their info
    private String name;
    private String url;

}

