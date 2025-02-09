package com.city.management.service.domain.mapper;

import com.city.management.service.domain.entity.CityEntity;
import com.city.management.service.domain.entity.WasteCollectionEntity;
import com.city.management.service.domain.entity.WasteCollectorEntity;
import com.city.management.service.domain.entity.WasteTypeEntity;
import com.city.management.service.domain.model.WasteCollection;
import com.city.management.service.domain.model.WasteCollector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WasteDataMapper {
  public WasteCollector mapToWasteCollector(WasteCollectorEntity entity) {
    return WasteCollector.builder()
        .name(entity.getName())
        .email(entity.getEmail())
        .wasteCollections(entity.getCollections()
            .stream()
            .map(this::mapToWasteCollection)
            .toList())
        .build();
  }

  private WasteCollection mapToWasteCollection(WasteCollectionEntity entity) {
    return WasteCollection.builder()
        .collectedAmount(entity.getCollectedAmount())
        .unit(entity.getUnit())
        .collectedOn(entity.getCollectedOn())
        .wasteType(entity.getWasteType() != null ? entity.getWasteType().getName() : null)
        .build();
  }

  public WasteCollectorEntity mapToWasteCollectorEntity(WasteCollector collector, CityEntity cityEntity) {
    WasteCollectorEntity wasteCollectorEntity = new WasteCollectorEntity();
    wasteCollectorEntity.setName(collector.getName());
    wasteCollectorEntity.setEmail(collector.getEmail());
    wasteCollectorEntity.setCity(cityEntity);

    List<WasteCollectionEntity> wasteCollectionEntities = collector.getWasteCollections()
        .stream()
        .map(collection -> mapToWasteCollectionEntity(collection, wasteCollectorEntity))
        .toList();

    wasteCollectorEntity.setCollections(wasteCollectionEntities);

    return wasteCollectorEntity;
  }

  private WasteCollectionEntity mapToWasteCollectionEntity(WasteCollection collection, WasteCollectorEntity collector) {
    WasteCollectionEntity wasteCollectionEntity = new WasteCollectionEntity();
    wasteCollectionEntity.setCollectedAmount(collection.getCollectedAmount());
    wasteCollectionEntity.setUnit(collection.getUnit());
    wasteCollectionEntity.setCollectedOn(collection.getCollectedOn());
    wasteCollectionEntity.setWasteCollector(collector);

    WasteTypeEntity wasteTypeEntity = new WasteTypeEntity();
    wasteTypeEntity.setName(collection.getWasteType());
    wasteTypeEntity.setCollections(List.of(wasteCollectionEntity));

    wasteCollectionEntity.setWasteType(wasteTypeEntity);

    return wasteCollectionEntity;
  }
}
