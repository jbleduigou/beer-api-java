package com.github.jbleduigou.beer.repository;

import com.github.jbleduigou.beer.model.Beer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {

  Beer findByName(String name);
}
