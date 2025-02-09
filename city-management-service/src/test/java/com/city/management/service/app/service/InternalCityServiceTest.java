package com.city.management.service.app.service;

import com.city.management.service.app.facet.repository.CityRepository;
import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.exception.ServiceException;
import com.city.management.service.domain.mapper.CityDataMapper;
import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InternalCityServiceTest {

  @InjectMocks
  InternalCityService internalCityService;

  @Mock
  CityRepository cityRepository;

  @Mock
  CityDataMapper cityDataMapper;

  private static final String CITY_ID = "mock-city-id";

  @Test
  void getStrategy_shouldReturnInternalEnum() {
    CityDataSourcingStrategyEnum strategyEnum = internalCityService.getStrategy();
    assertThat(strategyEnum).isEqualTo(CityDataSourcingStrategyEnum.INTERNAL);
  }

  @Test
  void getCityData_shouldReturnCityModel_whenFindEntityFromDatabase() {
    CityEntity mockCityEntity = new CityEntity();
    City mockCity = new City();

    when(cityRepository.findCityById(CITY_ID)).thenReturn(mockCityEntity);
    when(cityDataMapper.mapCityEntityData(mockCityEntity)).thenReturn(mockCity);

    City result = internalCityService.getCityData(CITY_ID);
    assertThat(result).isEqualTo(mockCity);
  }

  @Test
  void getCityData_shouldThrowException_whenNotFoundEntityFromDatabase() {
    when(cityRepository.findCityById(CITY_ID)).thenReturn(null);

    assertThrows(ServiceException.class, () -> internalCityService.getCityData(CITY_ID));
  }

  @Test
  void saveCityData_shouldReturnCityId_whenSaveDataSuccessfullyToDatabase() {
    City mockCityRequest = new City();
    CityEntity mockCityEntity = new CityEntity();
    mockCityEntity.setId(CITY_ID);

    when(cityDataMapper.mapCityRequest(mockCityRequest)).thenReturn(mockCityEntity);
    doNothing().when(cityRepository).save(anyList());

    String result = internalCityService.saveCityData(mockCityRequest);
    assertThat(result).isEqualTo(CITY_ID);
  }

  @Test
  void saveCityData_shouldThrowException_whenErrorOccurWhileSavingData() {
    doThrow(new ServiceException("Custom message", "E123")).when(cityRepository).save(anyList());

    assertThrows(ServiceException.class, () -> internalCityService.saveCityData(new City()));
  }
}
