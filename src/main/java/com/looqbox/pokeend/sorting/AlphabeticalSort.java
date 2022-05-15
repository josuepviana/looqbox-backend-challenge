package com.looqbox.pokeend.sorting;

import com.looqbox.pokeend.model.PokemonResponse;
import java.util.Comparator;

// Here it is comparing the UNICODE values of each char in the name as to decide the alphabetical order
public final class AlphabeticalSort implements Comparator<PokemonResponse> {

    @Override
    public int compare(final PokemonResponse left, final PokemonResponse right) {
        return left.getName().compareToIgnoreCase(right.getName());
    }

}
