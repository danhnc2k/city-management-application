package com.city.management.service.app.service;

import com.city.management.service.domain.model.CityDataSourcingStrategyEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CityDataSourcingStrategy {
  private final Map<CityDataSourcingStrategyEnum, CityService> strategyMap;

  public CityDataSourcingStrategy(List<CityService> strategies) {
    this.strategyMap = strategies.stream().collect(Collectors.toMap(CityService::getStrategy, strategy -> strategy));
  }

  public CityService getCityService(CityDataSourcingStrategyEnum source) {
    CityService defaultService = strategyMap.get(CityDataSourcingStrategyEnum.INTERNAL);
    CityService cityService = strategyMap.get(source);
    return cityService == null ? defaultService : cityService;
  }
}
