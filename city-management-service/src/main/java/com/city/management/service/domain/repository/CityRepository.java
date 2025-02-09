package com.city.management.service.domain.repository;

import com.city.management.service.domain.entity.CityEntity;

import java.util.List;

public interface CityRepository {

  CityEntity findCityById(String id);

  void save(List<CityEntity> cityEntities);
}
