package com.city.management.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CityDataSourcingStrategyEnum {
  INTERNAL("Internal"),
  EXTERNAL("External");

  private final String strategy;
}
