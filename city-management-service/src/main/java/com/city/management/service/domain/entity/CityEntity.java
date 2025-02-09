package com.city.management.service.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "city", schema = "citymanagement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {
  @Id
  @NotNull
  private String id;

  @NotNull
  @Column(name = "name")
  private String name;

  @NotNull
  @Column(name = "state")
  private String state;

  @NotNull
  @Column(name = "country")
  private String country;

  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<WasteCollectorEntity> wasteCollectors;
}
