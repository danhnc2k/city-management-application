package com.city.management.service.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "waste_collection", schema = "citymanagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WasteCollectionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "collected_amount")
  private BigDecimal collectedAmount;

  @NotNull
  @Column(name = "unit")
  private String unit;

  @NotNull
  @Column(name = "collected_on")
  private String collectedOn;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "collector_id")
  WasteCollectorEntity wasteCollector;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "type_id")
  WasteTypeEntity wasteType;
}
