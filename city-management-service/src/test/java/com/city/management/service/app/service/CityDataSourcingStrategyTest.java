package com.city.management.service.app.service;

import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityDataSourcingStrategyTest {
  private CityDataSourcingStrategy cityDataSourcingStrategy;

  @BeforeEach
  void setUp() {
    cityDataSourcingStrategy = new CityDataSourcingStrategy(List.of(
        new InternalCityService(null, null),
        new ExternalCityService()
    ));
  }

  @Test
  void getCityService_shouldReturnInternalCityService_whenReceiveSourceIsInternal() {
    CityService service = cityDataSourcingStrategy.getCityService(CityDataSourcingStrategyEnum.INTERNAL);
    assertThat(service).isInstanceOf(InternalCityService.class);
  }

  @Test
  void getCityService_shouldReturnInternalCityService_whenReceiveSourceIsNull() {
    CityService service = cityDataSourcingStrategy.getCityService(null);
    assertThat(service).isInstanceOf(InternalCityService.class);
  }
}
