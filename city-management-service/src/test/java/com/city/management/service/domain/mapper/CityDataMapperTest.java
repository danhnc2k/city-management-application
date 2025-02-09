package com.city.management.service.domain.mapper;

import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.entity.WasteCollectorEntity;
import com.city.management.service.domain.model.City;
import com.city.management.service.domain.model.WasteCollector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CityDataMapperTest {

  @InjectMocks
  CityDataMapper cityDataMapper;

  @Mock
  WasteDataMapper wasteDataMapper;

  private static final String MOCK_ID = "mock-id";
  private static final String MOCK_NAME = "mock-name";
  private static final String MOCK_STATE = "mock-state";
  private static final String MOCK_COUNTRY = "mock-country";

  @Test
  void mapCityEntityData_shouldReturnCityModel() {
    WasteCollector mockWasteCollector = new WasteCollector();

    when(wasteDataMapper.mapToWasteCollector(any())).thenReturn(mockWasteCollector);
    City result = cityDataMapper.mapCityEntityData(createMockCityEntity());

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(MOCK_ID);
    assertThat(result.getName()).isEqualTo(MOCK_NAME);
    assertThat(result.getState()).isEqualTo(MOCK_STATE);
    assertThat(result.getCountry()).isEqualTo(MOCK_COUNTRY);
    assertThat(result.getWasteCollectors()).hasSize(1);
    assertThat(result.getWasteCollectors().get(0)).isEqualTo(mockWasteCollector);
  }

  @Test
  void mapCityRequest_shouldReturnCityEntity() {
    WasteCollectorEntity mockWasteCollector = new WasteCollectorEntity();

    when(wasteDataMapper.mapToWasteCollectorEntity(any(), any())).thenReturn(mockWasteCollector);
    CityEntity result = cityDataMapper.mapCityRequest(createMockCity());

    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(MOCK_ID);
    assertThat(result.getName()).isEqualTo(MOCK_NAME);
    assertThat(result.getState()).isEqualTo(MOCK_STATE);
    assertThat(result.getCountry()).isEqualTo(MOCK_COUNTRY);
    assertThat(result.getWasteCollectors()).hasSize(1);
    assertThat(result.getWasteCollectors().get(0)).isEqualTo(mockWasteCollector);
  }

  private CityEntity createMockCityEntity() {
    CityEntity cityEntity = new CityEntity();
    cityEntity.setId(MOCK_ID);
    cityEntity.setName(MOCK_NAME);
    cityEntity.setState(MOCK_STATE);
    cityEntity.setCountry(MOCK_COUNTRY);
    cityEntity.setWasteCollectors(List.of(new WasteCollectorEntity()));
    return cityEntity;
  }
  
  private City createMockCity() {
    return City.builder()
        .id(MOCK_ID)
        .name(MOCK_NAME)
        .state(MOCK_STATE)
        .country(MOCK_COUNTRY)
        .wasteCollectors(List.of(new WasteCollector()))
        .build();
  }
}
