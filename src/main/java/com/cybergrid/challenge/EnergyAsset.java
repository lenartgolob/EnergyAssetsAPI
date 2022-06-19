package com.cybergrid.challenge;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TimeSeries;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
@TimeSeries(collection="energyAsset", timeField = "timestamp")
public class EnergyAsset {
    @Id
    private String id;
    private LocalDateTime timepoint;
    private BigDecimal active_power;
    private BigDecimal voltage;

    public EnergyAsset(LocalDateTime timepoint, BigDecimal active_power, BigDecimal voltage) {
        this.timepoint = timepoint;
        this.active_power = active_power;
        this.voltage = voltage;
    }
}
