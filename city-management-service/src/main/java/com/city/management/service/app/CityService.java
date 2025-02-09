package com.city.management.service.app;

import com.city.management.service.domain.constant.Constants;
import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.entity.WasteCollectorEntity;
import com.city.management.service.domain.exception.ServiceException;
import com.city.management.service.domain.mapper.WasteDataMapper;
import com.city.management.service.domain.model.City;
import com.city.management.service.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityService {
  private final CityRepository cityRepository;
  private final WasteDataMapper wasteDataMapper;

  public City getCityById(String id) {
    CityEntity entity = cityRepository.findCityById(id);

    if (entity == null) {
      String errorMessage = String.format("Cannot find city by id=%s", id);
      throw new ServiceException(errorMessage, Constants.ENTITY_NOT_FOUND_ERROR_CODE);
    }

    return mapCityEntityData(entity);
  }

  public String saveCity(City cityRequest) {
    CityEntity cityEntity = mapCityRequest(cityRequest);
    cityRepository.save(List.of(cityEntity));
    return cityEntity.getId();
  }

  private CityEntity mapCityRequest(City cityRequest) {
    CityEntity cityEntity = new CityEntity();
    cityEntity.setId(UUID.randomUUID().toString());
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

  private City mapCityEntityData(CityEntity entity) {
    return City.builder()
        .name(entity.getName())
        .state(entity.getState())
        .country(entity.getCountry())
        .wasteCollectors(entity.getWasteCollectors()
            .stream()
            .map(wasteDataMapper::mapToWasteCollector)
            .toList())
        .build();
  }
}
