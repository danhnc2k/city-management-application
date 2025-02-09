package com.city.management.service.infra.external;

import com.city.management.service.app.facet.downstream.ExternalDataSource;
import com.city.management.service.domain.model.City;
import com.city.management.service.infra.external.mapper.ExternalCityDataMapper;
import com.city.management.service.infra.external.model.ExternalCityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalCityDataSourceImplementation implements ExternalDataSource {
  private final ExternalCityClient externalCityClient;
  private final ExternalCityDataMapper externalCityDataMapper;

  @Override
  public City getCityData(String cityId) {
    ExternalCityModel externalCityModel = externalCityClient.getCityData(cityId).getBody();
    return externalCityDataMapper.mapExternalCityDataModel(externalCityModel);
  }
}
