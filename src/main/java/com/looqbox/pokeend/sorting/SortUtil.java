package com.looqbox.pokeend.sorting;

import com.looqbox.pokeend.model.PokemonResponse;
import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Objects;

// Quick Sort is a simple and fast algorithm for ordering items
// It sets an item as a pivot in the center, and sorts all other items such as
// smaller items will be on the left and higher ones on the right with the pivot in the middle.

@UtilityClass
public final class SortUtil {

  private final AlphabeticalSort ALPHABETICAL_SORT = new AlphabeticalSort();
  private final LengthSort LENGTH_SORT = new LengthSort();

  public void quickSort(List<PokemonResponse> list, SortType sortType) {
    quickSort(list, null, null, sortType);
  }

  // Moves the names around the list
  private void invertPosition(List<PokemonResponse> pokemonList, int first, int second) {
    PokemonResponse pokemon = pokemonList.get(first);

    pokemonList.set(first, pokemonList.get(second));
    pokemonList.set(second, pokemon);
  }

  private int movePosition(List<PokemonResponse> pokemonList, int start, int end, SortType sortType) {
    PokemonResponse center = pokemonList.get(end);

    int pos = start;
    for (int  i = start; i < end; i++) {

      // Compares names based on the total length of the string
      if (sortType == SortType.LENGTH && LENGTH_SORT.compare(pokemonList.get(i), center) <= 0) {
        invertPosition(pokemonList, i, pos);
        pos++;
        continue;
      }

      // Compares names based on UNICODE numbers
      if (sortType == SortType.ALPHABETICAL && ALPHABETICAL_SORT.compare(pokemonList.get(i), center) <= 0) {
        invertPosition(pokemonList, i, pos);
        pos++;
      }
    }

    invertPosition(pokemonList, pos, end);
    return pos;
  }

  private void quickSort(List<PokemonResponse> PokemonList, Integer startPos, Integer endPos, SortType sortType) {

    // If the pokemonList retrieved has 1 pokemon or less there's nothing to sort, so it just returns the list
    if (PokemonList.size() <= 1) {
      return;
    }

    int start = Objects.requireNonNullElse(startPos, 0);
    int end = Objects.requireNonNullElseGet(endPos, () -> PokemonList.size() - 1);

    // Breaks after it reaches the end
    if (start > end) {
      return;
    }

    int pivot = movePosition(PokemonList, start, end, sortType);

    quickSort(PokemonList, start, pivot - 1, sortType);
    quickSort(PokemonList, pivot + 1, end, sortType);
  }
}