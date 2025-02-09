package com.city.management.service.app.service;

import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExternalCityServiceTest {
  @InjectMocks
  ExternalCityService externalCityService;

  @Test
  void getStrategy_shouldReturnExternalEnum() {
    CityDataSourcingStrategyEnum strategyEnum = externalCityService.getStrategy();
    assertThat(strategyEnum).isEqualTo(CityDataSourcingStrategyEnum.EXTERNAL);
  }

  @Test
  void getCityData_shouldReturnNull() {
    assertNull(externalCityService.getCityData("mock-id"));
  }

  @Test
  void saveCityData_shouldReturnEmptyString() {
    assertNull(externalCityService.saveCityData(new City()));
  }
}
