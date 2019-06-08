package com.github.jbleduigou.beer.service;

import com.github.jbleduigou.beer.exception.EntityNotFoundException;
import com.github.jbleduigou.beer.model.Beer;
import com.github.jbleduigou.beer.repository.BeerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerService {

  private final BeerRepository repository;

  @Autowired
  public BeerService(BeerRepository repository) {
    this.repository = repository;
  }

  public List<Beer> getAllBeers() {
    return repository.findAll();
  }

  public List<Beer> getAllNonAlcoholicBeers() {
    return repository.findAll().stream()
            .filter(Beer::isAlcoholFree)
            .collect(Collectors.toList());
  }

  public Beer getBeerById(@NotNull Long beerId) {
    return repository.findById(beerId).orElseThrow(() -> new EntityNotFoundException("Beer", beerId));
  }
}
