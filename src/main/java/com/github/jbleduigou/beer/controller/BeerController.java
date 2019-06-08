package com.github.jbleduigou.beer.controller;

import com.github.jbleduigou.beer.model.Beer;
import com.github.jbleduigou.beer.service.BeerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {

  private final BeerService service;

  public BeerController(BeerService service) {
    this.service = service;
  }

  @GetMapping("/")
  public List<Beer> getAllBeers() {
    return service.getAllBeers();
  }

  @GetMapping("/non-alcoholic")
  public List<Beer> getAllNonAlcoholicBeers() {
    return service.getAllNonAlcoholicBeers();
  }

}
