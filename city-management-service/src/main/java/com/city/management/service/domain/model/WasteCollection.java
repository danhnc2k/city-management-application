package com.city.management.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WasteCollection {
  private BigDecimal collectedAmount;
  private String unit;
  private String collectedOn;
  private String wasteType;
}
