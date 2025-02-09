package com.city.management.service.infra.controller;

import com.city.management.service.app.CityService;
import com.city.management.service.domain.constant.Constants;
import com.city.management.service.domain.model.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.city.management.service.domain.constant.Constants.CORRELATION_ID_HEADER;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CityController {
  private final CityService cityService;

  @GetMapping("/v1/city/retrieve")
  public ResponseEntity<City> getCity(@RequestParam final MultiValueMap<String, String> queryParams,
                                      @RequestHeader(name = CORRELATION_ID_HEADER, required = false) String correlationId) {
    MDC.put(CORRELATION_ID_HEADER, correlationId);
    String cityId = queryParams.getFirst(Constants.CITY_ID_PARAM);
    City city = cityService.getCityById(cityId);
    return ResponseEntity.ok(city);
  }

  @PostMapping("/v1/city/create")
  public ResponseEntity<String> updateCity(@RequestHeader(name = CORRELATION_ID_HEADER, required = false) String correlationId,
                                         @RequestBody City cityRequest) {
    MDC.put(CORRELATION_ID_HEADER, correlationId);
    String cityId = cityService.saveCity(cityRequest);
    String message = String.format("Successfully save city data cityId=%s", cityId);
    return ResponseEntity.ok(message);
  }
}
