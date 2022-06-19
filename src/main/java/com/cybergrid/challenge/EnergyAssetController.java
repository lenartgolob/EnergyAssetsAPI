package com.cybergrid.challenge;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/energy-assets")
@AllArgsConstructor
public class EnergyAssetController {

    private final EnergyAssetService energyAssetService;

    @GetMapping
    public List<EnergyAsset> fetchAllEnergyAssets() {
        return energyAssetService.getAllEnergyAssets();
    }

    @GetMapping("/{id}")
    public EnergyAsset fetchEnergyAsset(@PathVariable String id) {
        return energyAssetService.getEnergyAsset(id);
    }

    @PostMapping
    public EnergyAsset createEnergyAsset(@RequestBody EnergyAsset energyAsset) {
        return energyAssetService.postEnergyAsset(energyAsset);
    }

    @PutMapping("/{id}")
    public EnergyAsset editEnergyAsset(@RequestBody EnergyAsset energyAsset, @PathVariable String id) {
        return energyAssetService.putEnergyAsset(energyAsset, id);
    }

    @DeleteMapping(("/{id}"))
    public void deleteEnergyAsset(@PathVariable String id) {
        energyAssetService.deleteEnergyAsset(id);
    }


}
