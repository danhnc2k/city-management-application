package com.city.management.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City {
  private String name;
  private String state;
  private String country;
  private List<WasteCollector> wasteCollectors;
}
