package com.github.jbleduigou.beer.matchers;

import com.github.jbleduigou.beer.model.Beer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Objects;

class BeerWithName extends TypeSafeMatcher<Beer> {

  private final String name;

  BeerWithName(String name) {
    this.name = name;
  }

  @Override
  protected boolean matchesSafely(Beer beer) {
    return Objects.equals(beer.getName(), name);
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("beer with name ").appendValue(name);
  }

  @Override
  protected void describeMismatchSafely(Beer item, Description mismatchDescription) {
    mismatchDescription.appendText("name was ");
    mismatchDescription.appendValue(item.getName());
  }
}
