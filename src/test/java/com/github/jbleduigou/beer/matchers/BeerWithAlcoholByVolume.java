package com.github.jbleduigou.beer.matchers;

import com.github.jbleduigou.beer.model.Beer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

class BeerWithAlcoholByVolume extends TypeSafeMatcher<Beer> {

  private final Double alcoholByVolume;

  BeerWithAlcoholByVolume(Double alcoholByVolume) {
    this.alcoholByVolume = alcoholByVolume;
  }

  @Override
  protected boolean matchesSafely(Beer beer) {
    return beer != null && Objects.equals(beer.getAlcoholByVolume(), alcoholByVolume);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("beer with alcoholByVolume ").appendValue(alcoholByVolume);
  }

  @Override
  protected void describeMismatchSafely(Beer item, Description mismatchDescription) {
    mismatchDescription.appendText("but alcoholByVolume was ");
    mismatchDescription.appendValue(item.getAlcoholByVolume());
  }
}
