package com.github.jbleduigou.beer.data;

import com.github.jbleduigou.beer.model.Beer;
import com.github.jbleduigou.beer.repository.BeerRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InitialDataLoaderTest {

  @InjectMocks
  private InitialDataLoader dataLoader;

  @Mock
  private BeerRepository repository;

  @Test
  public void shouldCreateBeerGivenNotFound() {
    dataLoader.createBeerIfNotFound("Nanny State", 0.5);

    verify(repository).findByName("Nanny State");
    verify(repository).save(new Beer(null, "Nanny State", 0.5));
    verifyNoMoreInteractions(repository);
  }

  @Test
  public void shouldNotCreateBeerGivenFound() {
    when(repository.findByName("Nanny State")).thenReturn(new Beer(9531L, "Nanny State", 0.5));

    dataLoader.createBeerIfNotFound("Nanny State", 0.5);

    verify(repository).findByName("Nanny State");
    verifyNoMoreInteractions(repository);
  }

  @Test
  public void shouldLoadDataOnlyOnce() {
    dataLoader.onApplicationEvent(null);

    reset(repository);

    dataLoader.onApplicationEvent(null);

    verifyNoMoreInteractions(repository);
  }
}
