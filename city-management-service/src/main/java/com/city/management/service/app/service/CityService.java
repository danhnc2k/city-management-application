package com.city.management.service.app.service;


import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;

public interface CityService {

  CityDataSourcingStrategyEnum getStrategy();

  City getCityData(String cityId);

  String saveCity(City cityRequest);
}
