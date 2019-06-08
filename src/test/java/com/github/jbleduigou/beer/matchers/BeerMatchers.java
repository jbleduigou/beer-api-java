package com.github.jbleduigou.beer.matchers;

import com.github.jbleduigou.beer.model.Beer;

import org.hamcrest.Matcher;

public class BeerMatchers {

  public static Matcher<Beer> beerWithName(final String name) {
    return new BeerWithName(name);
  }

  public static Matcher<Beer> beerWithAbv(final Double alcoholByVolume) {
    return new BeerWithAlcoholByVolume(alcoholByVolume);
  }
}
