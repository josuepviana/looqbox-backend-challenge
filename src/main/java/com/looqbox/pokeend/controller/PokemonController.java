package com.looqbox.pokeend.controller;

import com.looqbox.pokeend.model.PokemonResult;
import com.looqbox.pokeend.service.PokeendService;
import com.looqbox.pokeend.sorting.SortType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("pokemons")
@RestController
public class PokemonController {
    private final PokeendService pokeendService;

    @ApiOperation("Search Pokemons by query string and sort type")
    @GetMapping
    public List<PokemonResult> searchPokeByName(
            @ApiParam("Query string to search")
            @RequestParam("q") String query,
            @ApiParam("Sorting algorithm to apply")
            @RequestParam("sort") SortType sortType) {

        return pokeendService.searchPokeByName(query, sortType);
    }
}
