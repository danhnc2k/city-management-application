package com.city.management.service.infra.external;

import com.city.management.service.domain.model.City;
import com.city.management.service.infra.external.mapper.ExternalCityDataMapper;
import com.city.management.service.infra.external.model.ExternalCityModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExternalCityDataSourceImplementationTest {

  @InjectMocks
  ExternalCityDataSourceImplementation externalCityDataSourceImplementation;

  @Mock
  ExternalCityClient externalCityClient;

  @Mock
  ExternalCityDataMapper externalCityDataMapper;

  @Test
  void getCityData_shouldReturnExternalCityModel_whenReceiveCityId() {
    String mockCityId = "mock-city-id";
    ExternalCityModel mockExternalCityModel = new ExternalCityModel();

    when(externalCityClient.getCityData(mockCityId)).thenReturn(ResponseEntity.ok(mockExternalCityModel));
    when(externalCityDataMapper.mapExternalCityDataModel(mockExternalCityModel)).thenReturn(new City());

    assertNotNull(externalCityDataSourceImplementation.getCityData(mockCityId));
  }

}
