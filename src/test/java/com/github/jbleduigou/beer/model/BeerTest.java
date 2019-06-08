package com.github.jbleduigou.beer.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BeerTest {

  private final Beer beer = new Beer();

  @Before
  public void setFields() {
    beer.setId(1337L);
    beer.setName("Junit Ale");
    beer.setAlcoholByVolume(4.0);
  }

  @Test
  public void hashCodeTesting() {
    assertThat(beer.hashCode(), is(new Beer(1337L, "Junit Ale", 4.0).hashCode()));
  }

  @Test
  public void hashCodeTestingNullFields() {
    assertThat(new Beer().hashCode(), is(357642));
  }

  @Test
  public void toStringTesting() {
    assertThat(beer.toString(), is("Beer(id=1337, name=Junit Ale, alcoholByVolume=4.0)"));
  }

  @Test
  public void equalsShouldReturnFalseGivenNull() {
    assertThat(beer.equals(null), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenString() {
    assertThat(beer.equals("A STRING"), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenIdDifferent() {
    assertThat(beer.equals(new Beer(1L, "Junit Ale", 4.0)), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenNameDifferent() {
    assertThat(beer.equals(new Beer(1337L, "Another Junit Ale", 4.0)), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenAbvDifferent() {
    assertThat(beer.equals(new Beer(1337L, "Junit Ale", 6.5)), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenIdNull() {
    assertThat(beer.equals(new Beer(null, "Junit Ale", 4.0)), is(false));
    assertThat(new Beer(null, "Junit Ale", 4.0).equals(beer), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenNameNull() {
    assertThat(beer.equals(new Beer(1337L, null, 4.0)), is(false));
    assertThat(new Beer(1337L, null, 4.0).equals(beer), is(false));
  }

  @Test
  public void equalsShouldReturnFalseGivenAbvNull() {
    assertThat(beer.equals(new Beer(1337L, "Junit Ale", null)), is(false));
    assertThat(new Beer(1337L, "Junit Ale", null).equals(beer), is(false));
  }

  @Test
  public void equalsShouldReturnTrueGivenSameInstance() {
    assertThat(beer.equals(beer), is(true));
  }

  @Test
  public void equalsShouldReturnTrueGivenSameFields() {
    assertThat(beer.equals(new Beer(1337L, "Junit Ale", 4.0)), is(true));
  }

  @Test
  public void equalsShouldReturnTrueGivenAllFieldsNull() {
    assertThat(new Beer(null, null, null).equals(new Beer(null, null, null)), is(true));
  }

  @Test
  public void shouldNotBeAlcoholFree() {
    assertThat(beer.isAlcoholFree(), is(false));
  }

  @Test
  public void shouldNotBeAlcoholFreeGivenNull() {
    assertThat(new Beer().isAlcoholFree(), is(false));
  }
}
