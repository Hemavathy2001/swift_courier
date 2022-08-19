package com.example.swiftcourier.controller;



import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.commonconstants.SCExceptionConstants;
import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.handler.SuccessResponseHandler;
import com.example.swiftcourier.service.CouponService;


/**
 * 
 * Handles rest API end points with respect to coupons
 *
 */
@RestController
public class CouponController {
	
    /**
     * 
     * Service to access and add all Coupon Details via Coupon Repository 
     * 
     */
	@Autowired 
	CouponService couponService;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponController.class);

	
	
	    /**
	     * adding coupons to database -> accessed by manager
	     * @param coupon
	     * @return
	     */
		@PostMapping(value="/coupons")
		@RolesAllowed("ROLE_MANAGER")
		public  ResponseEntity<Object> addCoupon(@RequestBody Coupon coupon) {
			try {
			couponService.addCoupon(coupon);
			return SuccessResponseHandler.generateResponse(CommonConstants.CouponAdded, HttpStatus.CREATED);
			}
			catch(Exception e ){
				LOGGER.error(CommonConstants.Error+" "+e);
				return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002,HttpStatus.BAD_REQUEST);
			}
			
		}
		
		/**
		 *  view specific coupon -> accessed by manager 
		 * @param couponId
		 * @return
		 */
		@GetMapping(value="/coupons/{couponId}")
		@RolesAllowed("ROLE_MANAGER")
		public ResponseEntity<Object> getCoupon(@PathVariable int couponId) {	
			try {
			        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponService.getCoupon(couponId));
			}
			catch(Exception e ){
				LOGGER.error(CommonConstants.Error+" "+e);
				return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.NOT_FOUND );
			}
			
		}
		
		/**
		 * view all coupons -> accessed  by manager , office boy	
		 * @return
		 */
		@GetMapping(value="/coupons")
		@RolesAllowed({"ROLE_MANAGER", "ROLE_OFFICEBOY"})
		public ResponseEntity<Object>  getAllCoupons(){
			try {
		        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponService.getAllCoupon());
		    }
		    catch(Exception e ){
		    	LOGGER.error(CommonConstants.Error+" "+e);
			    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.NOT_FOUND );
	     	}
			
		}
		

		
		/**
		 *  view all  unExpired coupon -> accessed by Manager 	
		 * @return
		 */
		@GetMapping(value="/coupons/id/valid")
		@RolesAllowed("ROLE_MANAGER")
		public ResponseEntity<Object>  getUnExpiredCoupon(){
			try {
		        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponService.getUnExpiredCoupon());
		    }
		    catch(Exception e ){
		    	LOGGER.error(CommonConstants.Error+" "+e);
			    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.NOT_FOUND );
	     	}
			
		}

		/**
		 * view whether the coupon is eligible or not for the user -> accessed  by manager , office boy
		 * @param couponId
		 * @return
		 */
		@RolesAllowed({"ROLE_MANAGER", "ROLE_OFFICEBOY"})
		@GetMapping(value = "/coupons/eligible")
		public ResponseEntity<Object>  checkEligiblity(@RequestParam("id") int couponId) {
			
			try {
		        return SuccessResponseHandler.generateResponse(CommonConstants.CouponData,HttpStatus.OK,couponService.checkEligiblity(couponId));
		    }
		    catch(Exception e ){
		    	LOGGER.error(CommonConstants.Error+" "+e);
			    return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC002, HttpStatus.NOT_FOUND );
	     	}
			
		}
		
}
