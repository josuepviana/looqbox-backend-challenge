package com.looqbox.pokeend.service;

import com.looqbox.pokeend.model.PokemonPage;
import com.looqbox.pokeend.model.PokemonResponse;
import com.looqbox.pokeend.model.PokemonResult;
import com.looqbox.pokeend.sorting.SortType;
import com.looqbox.pokeend.sorting.SortUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PokeendService {

    // Defines the limit to bring ALL pokemon in a single query instead of hard-coding an especific number
    private final int LIMIT = -1;

    // Created a new instance of the WebClient as 'pokeAPI' with previously configured URL
    private final WebClient pokeAPI;

    public Mono<PokemonPage> getPage() {

        // Defined the request method and retrieves the pokemons
        return pokeAPI.method(HttpMethod.GET)
                .uri(builder -> builder.queryParam("limit", LIMIT).build())
                .retrieve()
                .bodyToMono(PokemonPage.class);
    }

    // Recovers the pokemon list with all of them
    // applies the filter and sorting algorithm then builds the return object
    public List<PokemonResult> searchPokeByName(String query, SortType sortType) {

        return getPage()
                .flatMap(page -> Mono.just(filterByName(page.getResults(), query)))
                .flatMap(pokemons -> Mono.just(sorted(pokemons, sortType)))
                .flatMap(pokemons -> Mono.just(PokemonResult.fromPokemonList(pokemons, query)))
                .block();
    }

    // Filters the items with the specified query as part of its name and adds it to a collection
    // EX: Query: "bulb" [_bulb_asaur, pikachu, squirtle] -> Returns: bulbasaur
    private List<PokemonResponse> filterByName(List<PokemonResponse> pokemons, String pokemonName) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getName().contains(pokemonName))
                .collect(Collectors.toList());
    }

    // Returns the sorted list of pokemon based on the user selected type
    private List<PokemonResponse> sorted(List<PokemonResponse> pokemons, SortType sortType) {
        SortUtil.quickSort(pokemons, sortType);

        return pokemons;
    }
}
