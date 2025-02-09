package com.city.management.service.app.service;


import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import com.city.management.service.domain.model.CreateCityResponse;

public interface CityService {

  CityDataSourcingStrategyEnum getStrategy();

  City getCityData(String cityId);

  CreateCityResponse saveCityData(City cityRequest);
}
