package com.backend.crop_price_backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.crop_price_backend.dto.DailyPriceDTO;
import com.backend.crop_price_backend.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long>{
	  	List<Price> findByCropIdOrderByDateAsc(Long cropId);

	    Price findTopByCropIdOrderByDateDesc(Long cropId);
	    
	    @Query("""
	    		SELECT new com.backend.crop_price_backend.dto.DailyPriceDTO(
	    		    p.date,
	    		    AVG(p.avgPrice)
	    		)
	    		FROM Price p
	    		WHERE p.crop.id = :cropId
	    		AND (:market IS NULL OR p.market = :market)
	    		GROUP BY p.date
	    		ORDER BY p.date ASC
	    		""")
	    		List<DailyPriceDTO> getDailyAveragePrices(
	    		        Long cropId,
	    		        String market
	    		);
	    @Query("""
	    		SELECT new com.backend.crop_price_backend.dto.DailyPriceDTO(
	    		    p.date,
	    		    AVG(p.avgPrice)
	    		)
	    		FROM Price p
	    		WHERE p.crop.id = :cropId
	    		GROUP BY p.date
	    		ORDER BY p.date ASC
	    		""")
	    		List<DailyPriceDTO> getDailyAveragePrices(
	    		        Long cropId
	    		);
	    
	    @Query("""
	    		SELECT DISTINCT p.market
	    		FROM Price p
	    		WHERE p.crop.id = :cropId
	    		ORDER BY p.market ASC
	    		""")
	    		List<String> getMarketsByCrop(Long cropId);
}
