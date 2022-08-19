package com.example.swiftcourier.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.swiftcourier.dao.BlockedToken;

/**
 * 
 * Repository that extends JPA -> with regard to BlockedToken DAO
 *
 */
@Repository
@Transactional
public interface BlockedTokenRepository extends JpaRepository<BlockedToken, Integer> {
	
    /**
     * This method returns token details from database
     * @param token
     * @return
     */
	BlockedToken findByTocken(String token);
    
	/**
	 * This method deletes the token from database
	 * @return
	 */
	@Modifying
	@Query("Delete from BlockedToken blockedToken where blockedToken.tokenExpiryDate< now()")
	Integer deleteByTokenExpiryDate();
    
    
}
