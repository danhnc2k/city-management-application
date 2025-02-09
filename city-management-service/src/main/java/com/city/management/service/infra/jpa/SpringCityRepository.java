package com.city.management.service.infra.jpa;

import com.city.management.service.domain.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringCityRepository extends JpaRepository<CityEntity, Long> {
  CityEntity findById(String name);
}
