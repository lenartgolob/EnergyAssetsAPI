package com.cybergrid.challenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(EnergyAssetRepository repository, MongoTemplate mongoTemplate) {
		return args -> {
			EnergyAsset energy = new EnergyAsset(LocalDateTime.now(), new BigDecimal("100"), new BigDecimal("100"));

/*			Query query = new Query();
			query.addCriteria(Criteria.where("Id").is("62aeddd6ed84da1d3646e1c2"));

			List<EnergyAsset> energyAssets = mongoTemplate.find(query, EnergyAsset.class);
			System.out.println("Size: " + energyAssets.size());
			for (EnergyAsset asset : energyAssets) {
				System.out.println(asset.getId());
			}
			if(energyAssets.isEmpty()) {
				repository.insert(energy);
			}*/
/*			repository.findEnergyAssetById("62af816c5f49841543e43cc4")
					.ifPresentOrElse(a -> {
						System.out.println("Id obstaja in sicer " + a);
					}, () -> {
						System.out.println("Ta id ne obstaja");
					});*/
/*
			repository.insert(energy);
*/

		};
	}
}
