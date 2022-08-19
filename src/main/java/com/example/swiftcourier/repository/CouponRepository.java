package com.example.swiftcourier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dao.Role;

/**
 * 
 * Repository that extends JPA -> with regard to Coupon DAO
 *
 */
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>{
   
	
	/**
	 * This method returns coupon based on role from database
	 * @param role
	 * @return
	 */
	List<Coupon> findByRole(Role role);
	
	
	
	/**
	 * This method returns coupons which is not expired 
	 * @return
	 */
	@Query("select coupon from Coupon coupon where coupon.couponExpiryDate >= DATE(now())")
	List<Coupon> findByCouponDate();

}
