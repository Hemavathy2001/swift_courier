package com.example.swiftcourier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.dao.User;

/**
 * 
 * Repository that extends JPA -> with regard to CourierDetails DAO
 *
 */
@Repository
public interface CourierDetailsRepository extends JpaRepository<CourierDetails, Integer> {
    
	/**
	 * This method returns Courier Details based on user Id from database 
	 * @param userId
	 * @return
	 */
	List<CourierDetails> findByUserId(int userId);
	
	
	/**
	 * This method returns Courier Details based on couponId and userId from database
	 * @param couponId
	 * @param userId
	 * @return
	 */
	List<CourierDetails> findByCouponIdAndUserId(Integer couponId, Integer userId);
	
	
	/**
	 * This method returns courier Details based on couponId from database
	 * @param couponId
	 * @return
	 */
	List<CourierDetails> findByCouponId(int couponId);

}