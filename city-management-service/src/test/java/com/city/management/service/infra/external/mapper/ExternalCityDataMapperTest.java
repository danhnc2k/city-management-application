package com.city.management.service.infra.external.mapper;

import com.city.management.service.infra.external.model.ExternalCityModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ExternalCityDataMapperTest {
  @InjectMocks
  ExternalCityDataMapper externalCityDataMapper;

  @Test
  void mapExternalCityDataModel_shouldReturnCity() {
    assertNotNull(externalCityDataMapper.mapExternalCityDataModel(new ExternalCityModel()));
  }
}
