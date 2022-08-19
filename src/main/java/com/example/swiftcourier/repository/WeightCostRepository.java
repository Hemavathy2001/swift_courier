package com.example.swiftcourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.WeightCost;

/**
 * 
 * Repository that extends JPA -> with regard to WeightCost DAO
 *
 */
@Repository
public interface WeightCostRepository extends JpaRepository<WeightCost, Integer> {
	
	/**
	 * This method returns weight from Database
	 * @param weight
	 * @return
	 */	
	@Query("select weight from WeightCost weight where weight.endGrams >=:weight AND weight.startGrams <=:weight")
	WeightCost findByWeight(long weight);

}
