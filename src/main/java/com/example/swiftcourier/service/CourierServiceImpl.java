package com.example.swiftcourier.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.controller.ConsignmentBookingController;
import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.dao.DeliveryType;
import com.example.swiftcourier.dao.DistanceCost;
import com.example.swiftcourier.dao.WeightCost;
import com.example.swiftcourier.repository.CouponRepository;
import com.example.swiftcourier.repository.CourierDetailsRepository;
import com.example.swiftcourier.repository.DeliveryTypeRepository;
import com.example.swiftcourier.repository.DistanceCostRepository;
import com.example.swiftcourier.repository.UserRepository;
import com.example.swiftcourier.repository.WeightCostRepository;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods adding and accessing courier details from data base
 *
 */
@Service
public class CourierServiceImpl implements CourierService {
	
	/** 
	 * To access coupon details via couponRepository
	 */
	@Autowired 
	CouponRepository couponRepository; 
	
	/**
	 * To access current user details 
	 */
	@Autowired 
	JwtTokenUtil JwtTokenUtil;
	
	/**
	 * To access courier details via courierDetailsRepository
	 */
	@Autowired
	CourierDetailsRepository courierDetailsRepository;
	
	/**
	 * To access user details via userRepository
	 */
	@Autowired 
	UserRepository userRepository;
	
	/**
	 * To access distance details via distanceCostRepository
	 */
	@Autowired 
	DistanceCostRepository distanceCostRepository;
	
	/**
	 * To access weight distance details via weightCostRepository
	 */
	@Autowired 
	WeightCostRepository weightCostRepository;
	
	/**
	 * To access delivery type details details via deliveryTypeRepository
	 */
	@Autowired 
	DeliveryTypeRepository deliveryTypeRepository;
	
	
	@Autowired 
	CouponService couponService;
    
	private static final Logger LOGGER = LoggerFactory.getLogger(CourierServiceImpl.class);
	
	/**
	 * 
	 * 
	 * 
	 * Method adds Courier details to database
	 * @param courierDetails
	 * 
	 * 
	 * 
	 */
	@Override
	public void addCourier(CourierDetails courierDetails) {
		

		        // delivery type charge
				courierDetails.setDeliveryTypePrice(calculateDeliveryTypePrice(courierDetails.getDeliveryType()));
				
				
				
				// weight charge
				courierDetails.setWeightPrice(calculateWeight(courierDetails.getWeight()));
				
				
				
				// booking date 
				Date date = new Date();
				courierDetails.setBookingDate(date);
				
				
				
				// userDetails
				courierDetails.setUser(JwtTokenUtil.getUserFromSecurityContext());
				
				
					
				Coupon coupon = getCoupon(courierDetails.getCouponId()).get();
					
				
				// calculating discount 
			    double discountAmount = 0;
			      

				if(couponService.checkEligiblity(courierDetails.getCouponId()) == (true))  {
			       coupon.setCouponUsedCount(coupon.getCouponUsedCount()+1);
			    	
					if(coupon.getDiscountType().equals("flat")) {
						discountAmount = coupon.getMaxReedembleAmountPerUsage();
					}
					else if(coupon.getDiscountType().equals("percentage")) {
	
						double percentAmount = ((double) coupon.getMaxRedeembleAmount()/100)* coupon.getPercentage();
					
						if(percentAmount>coupon.getMaxReedembleAmountPerUsage()) {
							discountAmount = coupon.getMaxReedembleAmountPerUsage();
						}
						else {
							discountAmount = percentAmount;
						}
					}
			    }
			    else {
			    	LOGGER.info(CommonConstants.couponNotEligible);
			    	 courierDetails.setCouponId(0);
			    }
			      
			    
			       
			    // discount amount
			    courierDetails.setDiscountAmount(discountAmount);
			     
			    
			    // is paid
				courierDetails.setPaid(true);
				
				
				//setting distance price
				courierDetails.setDistancePrice(calculateDistancePrice(courierDetails.getDistance()));
				
				
				// calculating total amount
				double amount = (courierDetails.getWeightPrice()+courierDetails.getDeliveryTypePrice()+courierDetails.getDistancePrice())-discountAmount;	
				
				
				//GST is applied
				amount =amount - ((amount/100)*5);
				
				
				//total amount set
				courierDetails.setTotalAmount(amount);
						
				
				// saving all details	
			  courierDetailsRepository.save(courierDetails);
	}
	
	
	   // returning price for distance 
		private int calculateDistancePrice(long distance) {
			DistanceCost distanceValue = distanceCostRepository.findByDistance(distance);	
			int distancePrice = (distanceValue==null) ? 1000 : distanceValue.getCost();
			LOGGER.info(CommonConstants.Distance);
			return distancePrice;
		}
		
		
		
		// returning weight price 
		private int calculateWeight(long weight) {			
			WeightCost weightValue = weightCostRepository.findByWeight(weight);
			int weightPrice = (weightValue==null) ? 1000 : weightValue.getCost();
			LOGGER.info(CommonConstants.Weight);
			return weightPrice;
			
		}
		
		
		
		//calculating delivery type price
		private int calculateDeliveryTypePrice(String deliveryType) {
			LOGGER.info(CommonConstants.DeliveryType);
			return deliveryTypeRepository.getByDeliveryType(deliveryType).getPrice();
		}

		
        // returns coupon details based on coupon id 
		private Optional<Coupon> getCoupon(int couponId) {
			return couponRepository.findById(couponId);
		}

}
