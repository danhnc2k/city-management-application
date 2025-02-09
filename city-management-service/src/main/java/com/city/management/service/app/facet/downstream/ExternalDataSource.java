package com.city.management.service.app.facet.downstream;


import com.city.management.service.domain.model.City;

public interface ExternalDataSource {
  City getCityData(String cityId);
}
