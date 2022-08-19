package com.example.swiftcourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.DeliveryType;


/**
 * 
 * Repository that extends JPA -> with regard to DeliveryType DAO
 *
 */
@Repository
public interface DeliveryTypeRepository extends JpaRepository<DeliveryType, Integer> {
	
	
    /**
     * This method returns delivery type details from database
     * @param deliveryType
     * @return
     */
	DeliveryType getByDeliveryType(String deliveryType);

}
