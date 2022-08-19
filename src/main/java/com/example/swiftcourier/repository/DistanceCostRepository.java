package com.example.swiftcourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftcourier.dao.DistanceCost;


/**
 * 
 * Repository that extends JPA -> with regard to DistanceCost DAO
 *
 */
@RestController
public interface DistanceCostRepository extends JpaRepository<DistanceCost, Integer>{
	
	/**
	 * This method returns Distance from Database
	 * @param distance
	 * @return
	 */ 
	@Query("select distance from DistanceCost distance where distance.distanceEnd >=:distance AND distance.distanceStart <=:distance")
	DistanceCost findByDistance(long distance);
    	
}
