package com.github.jbleduigou.beer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  private final String entityType;
  private final Long entityId;

  public EntityNotFoundException(String entityType, Long entityId) {
    super(String.format("%s with id=%d not found", entityType, entityId));
    this.entityType = entityType;
    this.entityId = entityId;
  }
}
