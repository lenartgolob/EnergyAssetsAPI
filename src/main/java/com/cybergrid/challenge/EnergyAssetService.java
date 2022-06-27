package com.cybergrid.challenge;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = {"EnergyAsset"})
public class EnergyAssetService {

    private final EnergyAssetRepository energyAssetRepository;
    private final MongoTemplate mongoTemplate;

    public List<EnergyAsset> getAllEnergyAssets() {
        return energyAssetRepository.findAll();
    }

    public EnergyAsset getEnergyAsset(String id) {
        return energyAssetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }

    @Cacheable("latest")
    public EnergyAsset getLatestEnergyAsset() {
        System.out.println("Getting latest energyAsset");
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "timepoint"));
        List<EnergyAsset> energyAssets = mongoTemplate.find(query, EnergyAsset.class);
        if(energyAssets.size() > 0) {
            return energyAssets.get(0);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    public List<EnergyAsset> getEnergyAssetsWithinTimePeriod(int minutes) {
        LocalDateTime timeNow = LocalDateTime.now();
        Query query = new Query();
        query.addCriteria(Criteria.where("timepoint").lt(timeNow.plusMinutes(minutes)).gt(timeNow.minusMinutes(minutes)));
        List<EnergyAsset> energyAssets = mongoTemplate.find(query, EnergyAsset.class);
        return energyAssets;
    }

    @CachePut("latest")
    public EnergyAsset postEnergyAsset(EnergyAsset energyAsset) {
        energyAsset.setTimepoint(LocalDateTime.now());
        return energyAssetRepository.insert(energyAsset);
    }

    public EnergyAsset putEnergyAsset(EnergyAsset energyAsset, String id) {
        return energyAssetRepository.findById(id)
                .map(energyAsset1 -> {
                    energyAsset1.setTimepoint(LocalDateTime.now());
                    energyAsset1.setActive_power(energyAsset.getActive_power());
                    energyAsset1.setVoltage(energyAsset.getVoltage());
                    return energyAssetRepository.save(energyAsset1);
                })
                .orElseGet(() -> {
                    energyAsset.setTimepoint(LocalDateTime.now());
                    energyAsset.setId(id);
                    return energyAssetRepository.save(energyAsset);
                });
    }

    public void deleteEnergyAsset(String id) {
        energyAssetRepository.deleteById(id);
    }

}
