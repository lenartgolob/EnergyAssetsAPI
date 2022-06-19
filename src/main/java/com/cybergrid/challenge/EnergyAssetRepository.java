package com.cybergrid.challenge;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnergyAssetRepository extends MongoRepository<EnergyAsset, String> {
    Optional<EnergyAsset> findEnergyAssetById(String Id);

}
