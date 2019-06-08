package com.github.jbleduigou.beer.data;


import com.github.jbleduigou.beer.model.Beer;
import com.github.jbleduigou.beer.repository.BeerRepository;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;

  private final BeerRepository beerRepository;

  public InitialDataLoader(BeerRepository beerRepository) {
    this.beerRepository = beerRepository;
  }

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup) {
      return;
    }

    createBeerIfNotFound("Punk IPA", 5.6);
    createBeerIfNotFound("Nanny State", 0.5);

    alreadySetup = true;
  }

  @Transactional
  void createBeerIfNotFound(String name, double abv) {
    Beer beer = beerRepository.findByName(name);
    if (beer == null) {
      beer = new Beer(null, name, abv);
      beerRepository.save(beer);
    }
  }
}
