package com.example.swiftcourier.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.example.swiftcourier.dto.CouponDTO;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods accessing coupon details from data base
 *
 */
@Service
public interface CouponDetailsService {
	
    /**
     * Method returns coupon that is used maximum number of times
     * @return
     */
	public List<CouponDTO> redemptionTimes() ;
	
	/**
	 * Method returns coupon  based on maximum redemption amount
	 * @return
	 */
	public List<CouponDTO> redemptionAmount();
	
	/**
	 * Method returns total worth of coupons used
	 * @return
	 */
	public double couponTotalWorth() ;
	
	/**
	 * Method returns coupon which is used maximum number of times based on role
	 * @return
	 */
	public String maxredemptionByType() ;
}
