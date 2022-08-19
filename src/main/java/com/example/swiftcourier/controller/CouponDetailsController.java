package com.example.swiftcourier.controller;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.commonconstants.SCExceptionConstants;
import com.example.swiftcourier.handler.SuccessResponseHandler;
import com.example.swiftcourier.service.CouponDetailsService;


/**
 * 
 * Handles  rest API end points with respect to coupons
 *
 */

@RestController
public class CouponDetailsController {
	
	/**
     * 
     * Service to access  all Coupon Details via Coupon Repository 
     * 
     */
	@Autowired 
    CouponDetailsService  couponDetailsService ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponDetailsController.class);
    
	/**
	 *  max redemption times a coupon used
	 * @return
	 */
	@RolesAllowed("ROLE_MANAGER")
	@GetMapping(value="/coupons/redemption/max")
	public ResponseEntity<Object> redemptionTimes(){
		
		try {
	        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponDetailsService.redemptionTimes());
	    }
	    catch(Exception e ){
	    	LOGGER.error(CommonConstants.Error+" "+e);
		    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.BAD_REQUEST );
     	}
		
	}
	
	/**
	 * Total worth of coupons used 
	 * @return
	 */
	@RolesAllowed("ROLE_MANAGER")
	@GetMapping(value="/coupons/worth/total")
	public ResponseEntity<Object>  totalWorth() {
		
		try {
	        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponDetailsService.couponTotalWorth());
	    }
	    catch(Exception e ){
	    	LOGGER.error(CommonConstants.Error+" "+e);
		    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.BAD_REQUEST );
     	}
		
	}
	
	
	/**
	 * max redemption amount a coupon
	 * @return
	 */
	@RolesAllowed("ROLE_MANAGER")
	@GetMapping(value="/coupons/max/amount")
	public ResponseEntity<Object>  highamount() {
		
		try {
	        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponDetailsService.redemptionAmount());
	    }
	    catch(Exception e ){
	    	LOGGER.error(CommonConstants.Error+" "+e);
		    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.BAD_REQUEST );
     	}
		
	}
	
	/**
	 * Statistics of usage by coupon type 
	 * @return
	 */
	@RolesAllowed("ROLE_MANAGER")
	@GetMapping(value="/coupons/redemption/max/type")
	public ResponseEntity<Object>  maxCountByType() {
		
		try {
	        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponDetailsService.maxredemptionByType());
	    }
	    catch(Exception e ){
	    	LOGGER.error(CommonConstants.Error+" "+e);
		    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.BAD_REQUEST );
     	}
		
	}

}
