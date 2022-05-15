package com.looqbox.pokeend.sorting;

import com.looqbox.pokeend.model.PokemonResponse;
import java.util.Comparator;

// Here it is comparing the total length of both names to decide which one comes first
public final class LengthSort implements Comparator<PokemonResponse> {

  @Override
  public int compare(final PokemonResponse left, final PokemonResponse right) {
    return Integer.compare(left.getName().length(), right.getName().length());
  }

}