package com.cybergrid.challenge;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class EnergyAssetService {

    private final EnergyAssetRepository energyAssetRepository;

    public List<EnergyAsset> getAllEnergyAssets() {
        return energyAssetRepository.findAll();
    }

    public EnergyAsset getEnergyAsset(String id) {
        return energyAssetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        ));
    }

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
