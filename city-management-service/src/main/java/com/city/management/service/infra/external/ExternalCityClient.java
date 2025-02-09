package com.city.management.service.infra.external;

import com.city.management.service.infra.external.model.ExternalCityModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "externalService")
public interface ExternalCityClient {

  @GetMapping(value = "v1/external/city/retrieve")
  ResponseEntity<ExternalCityModel> getCityData(@RequestParam("city_code") String cityCode);

}
