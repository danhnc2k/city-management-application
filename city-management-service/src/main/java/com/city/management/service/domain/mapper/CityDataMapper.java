package com.city.management.service.domain.mapper;

import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.entity.WasteCollectorEntity;
import com.city.management.service.domain.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CityDataMapper {
  private final WasteDataMapper wasteDataMapper;

  public City mapCityEntityData(CityEntity entity) {
    return City.builder()
        .id(entity.getId())
        .name(entity.getName())
        .state(entity.getState())
        .country(entity.getCountry())
        .wasteCollectors(entity.getWasteCollectors()
            .stream()
            .map(wasteDataMapper::mapToWasteCollector)
            .toList())
        .build();
  }

  public CityEntity mapCityRequest(City cityRequest) {
    CityEntity cityEntity = new CityEntity();
    String cityId = cityRequest.getId() == null ? UUID.randomUUID().toString() : cityRequest.getId();
    cityEntity.setId(cityId);
    cityEntity.setName(cityRequest.getName());
    cityEntity.setState(cityRequest.getState());
    cityEntity.setCountry(cityRequest.getCountry());

    List<WasteCollectorEntity> wasteCollectorEntities = cityRequest.getWasteCollectors()
        .stream()
        .map(wasteCollector -> wasteDataMapper.mapToWasteCollectorEntity(wasteCollector, cityEntity))
        .toList();

    cityEntity.setWasteCollectors(wasteCollectorEntities);

    return cityEntity;
  }
}
