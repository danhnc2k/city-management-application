package com.city.management.service.app.service;

import com.city.management.service.domain.constant.Constants;
import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.exception.ServiceException;
import com.city.management.service.domain.mapper.CityDataMapper;
import com.city.management.service.domain.model.City;
import com.city.management.service.app.facet.repository.CityRepository;
import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalCityService implements CityService {
  private final CityRepository cityRepository;
  private final CityDataMapper cityDataMapper;

  @Override
  public CityDataSourcingStrategyEnum getStrategy() {
    return CityDataSourcingStrategyEnum.INTERNAL;
  }

  @Override
  public City getCityData(String id) {
    CityEntity entity = cityRepository.findCityById(id);

    if (entity == null) {
      String errorMessage = String.format("Cannot find city by id=%s", id);
      throw new ServiceException(errorMessage, Constants.ENTITY_NOT_FOUND_ERROR_CODE);
    }

    return cityDataMapper.mapCityEntityData(entity);
  }

  @Override
  public String saveCity(City cityRequest) {
    try {
      CityEntity cityEntity = cityDataMapper.mapCityRequest(cityRequest);
      cityRepository.save(List.of(cityEntity));
      return cityEntity.getId();
    } catch (Exception ex) {
      log.error("[{}:{}] Error while saving city data", Constants.TECHNICAL_ERROR_TYPE, Constants.SAVE_CITY_ERROR_CODE);
      throw new ServiceException("Cannot save city data", Constants.SAVE_CITY_ERROR_CODE);
    }
  }
}
