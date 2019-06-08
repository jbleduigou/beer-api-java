package com.github.jbleduigou.beer.service;

import com.github.jbleduigou.beer.exception.EntityNotFoundException;
import com.github.jbleduigou.beer.model.Beer;
import com.github.jbleduigou.beer.repository.BeerRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.github.jbleduigou.beer.matchers.BeerMatchers.beerWithAbv;
import static com.github.jbleduigou.beer.matchers.BeerMatchers.beerWithName;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceTest {

  @InjectMocks
  private BeerService service;

  @Mock
  private BeerRepository repository;

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Before
  public void setupMocks() {
    when(repository.findAll()).thenReturn(
            Arrays.asList(new Beer(1337L, "Punk IPA", 5.6),
                    new Beer(9531L, "Nanny State", 0.5)));
    when(repository.findById(3457L)).thenReturn(Optional.empty());
    when(repository.findById(9531L)).thenReturn(Optional.of(new Beer(9531L, "Nanny State", 3.5)));
  }

  @Test
  public void getAllNonAlcoholicBeers() {
    List<Beer> results = service.getAllNonAlcoholicBeers();
    assertThat(results, hasItem(new Beer(9531L, "Nanny State", 0.5)));

    verify(repository).findAll();
    verifyNoMoreInteractions(repository);
  }

  @Test
  public void getAllBeers() {
    List<Beer> results = service.getAllBeers();
    assertThat(results, hasItem(new Beer(9531L, "Nanny State", 0.5)));
    assertThat(results, hasItem(new Beer(1337L, "Punk IPA", 5.6)));

    verify(repository).findAll();
    verifyNoMoreInteractions(repository);
  }

  @Test
  public void getBeerShouldThrowExceptionGivenNotFound() {
    exception.expectMessage("Beer with id=3457 not found");
    exception.expect(EntityNotFoundException.class);

    service.getBeerById(3457L);

    verify(repository).findById(3457L);
    verifyNoMoreInteractions(repository);
  }

  @Test
  public void getBeerShouldReturnNannyState() {
    Beer result = service.getBeerById(9531L);

    assertThat(result, both(beerWithName("Nanny State")).and(beerWithAbv(0.5)));

    verify(repository).findById(9531L);
    verifyNoMoreInteractions(repository);
  }
}
