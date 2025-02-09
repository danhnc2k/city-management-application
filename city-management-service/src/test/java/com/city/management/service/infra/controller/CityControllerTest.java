package com.city.management.service.infra.controller;

import com.city.management.service.app.service.CityDataSourcingStrategy;
import com.city.management.service.app.service.CityService;
import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import com.city.management.service.domain.model.CreateCityResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityControllerTest {

  @InjectMocks
  CityController cityController;

  @Mock
  CityDataSourcingStrategy cityDataSourcingStrategy;

  @Mock
  CityService cityService;

  private static final String CITY_ID = "mock-city-id";

  @Test
  void getCity_shouldReturnCityData_whenReceiveCityId() {
    City mockCity = new City();

    when(cityDataSourcingStrategy.getCityService(CityDataSourcingStrategyEnum.INTERNAL)).thenReturn(cityService);
    when(cityService.getCityData(CITY_ID)).thenReturn(mockCity);

    MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
    queryParams.put("city_id", List.of(CITY_ID));
    ResponseEntity<City> response = cityController.getCity(queryParams, "correlation-id");

    assertEquals(mockCity, response.getBody());
  }

  @Test
  void createCity_shouldReturnCityData_whenReceiveCityId() {
    City mockCityRequest = new City();
    CreateCityResponse mockResponse = CreateCityResponse.builder()
        .cityId(CITY_ID)
        .build();

    when(cityDataSourcingStrategy.getCityService(CityDataSourcingStrategyEnum.INTERNAL)).thenReturn(cityService);
    when(cityService.saveCityData(mockCityRequest)).thenReturn(mockResponse);

    ResponseEntity<CreateCityResponse> response = cityController.createCity("correlation-id", mockCityRequest);

    assertNotNull(response.getBody());
    assertEquals(CITY_ID, response.getBody().getCityId());
  }
}
