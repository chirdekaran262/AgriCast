package com.backend.crop_price_backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.crop_price_backend.model.Crop;

public interface CropRepository extends JpaRepository<Crop,Long> {
	 Optional<Crop> findByName(String commodity);
	
}
