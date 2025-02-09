package com.city.management.service.infra.external.mapper;

import com.city.management.service.domain.model.City;
import com.city.management.service.infra.external.model.ExternalCityModel;
import org.springframework.stereotype.Component;

@Component
public class ExternalCityDataMapper {
  public City mapExternalCityDataModel(ExternalCityModel externalCityModel) {
    return new City();
  }
}
