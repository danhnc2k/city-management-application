package com.city.management.service.app.service;

import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExternalCityService implements CityService {

  @Override
  public CityDataSourcingStrategyEnum getStrategy() {
    return CityDataSourcingStrategyEnum.EXTERNAL;
  }

  @Override
  public City getCityData(String cityId) {
    return null;
  }

  @Override
  public String saveCityData(City cityRequest) {
    return "";
  }
}
