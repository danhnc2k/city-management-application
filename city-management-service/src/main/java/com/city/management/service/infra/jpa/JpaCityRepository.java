package com.city.management.service.infra.jpa;

import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCityRepository implements CityRepository {
  private final SpringCityRepository springCityRepository;

  @Override
  public CityEntity findCityById(String id) {
    return springCityRepository.findById(id);
  }

  @Override
  public void save(List<CityEntity> cityEntities) {
    springCityRepository.saveAll(cityEntities);
  }
}
