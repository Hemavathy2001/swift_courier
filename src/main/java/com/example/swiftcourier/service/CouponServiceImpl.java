package com.example.swiftcourier.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.controller.CouponController;
import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.dao.User;
import com.example.swiftcourier.dto.CouponDTO;
import com.example.swiftcourier.repository.CouponRepository;
import com.example.swiftcourier.repository.CourierDetailsRepository;
import com.example.swiftcourier.repository.UserRepository;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods adding and accessing coupon details from data base
 */
@Service
public class CouponServiceImpl  implements CouponService{
	
	/**
	 *  To access coupon details via couponRepository
	 */
	@Autowired 
	CouponRepository couponRepository;
	
	/**
	 *  To access user details via user Repository
	 */
	@Autowired 
	UserRepository  userRepository;
	
	/**
	 *  To access courier details via courierDetailsRepository
	 */
	@Autowired
	CourierDetailsRepository courierDetailsRepository;
	
	/**
	 * To access current user details 
	 */
	@Autowired 
	JwtTokenUtil JwtTokenUtil;
	
	ModelMapper modelMapper = new ModelMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);
    
	
	/**
	 * 
     * Method adds coupon to database 
     * @param coupon
     * 
     */
	@Override
	public void addCoupon(Coupon coupon) {
		
	     List<User> users = userRepository.findAll();
		 Date date = new Date( );
		 coupon.setCouponIssueDate(date);
		 long month = TimeUnit.MILLISECONDS.convert(30, TimeUnit.DAYS);
		 Date expiry = new Date(System.currentTimeMillis() + month);
		 coupon.setCouponExpiryDate(expiry);
		 Set<User> usersList = new HashSet<>();
		 for(User user: users) {	
			 if(coupon.getRole().getName().equals(user.getRole().getName())) {
				 usersList.add(user);
			 }
		 }
		 coupon.setUser(usersList);
		 LOGGER.info(CommonConstants.CouponAdded);
		 couponRepository.save(coupon);	
	}
	
	public CouponDTO convertToDto(Coupon coupon) {
		CouponDTO couponDto = modelMapper.map(coupon, CouponDTO.class);
		return couponDto;
	}

	 /**
	 * 
     * method returns all the coupons from database
     * @return
     * 
     */
	@Override
	public List<CouponDTO> getAllCoupon() {
		List<Coupon> couponsList=couponRepository.findAll();
		List<CouponDTO> couponDto = new ArrayList<>();
		for(Coupon coupon : couponsList) {
				couponDto.add(convertToDto(coupon));			
	}
		LOGGER.info(CommonConstants.CouponData);
		return couponDto;
	}
    
	
	/**
	 * 
	 * method returns a particular coupon from data based on couponId 
	 * @param couponId
	 * @return
	 * 
	 */
	@Override
	public CouponDTO getCoupon(int couponId) {
		Coupon coupon = couponRepository.findById(couponId).get();
		LOGGER.info(CommonConstants.CouponData);
		return convertToDto(coupon) ;	
	}
   
	
	/**
	 * 
	 * Method returns all unexpired coupon from database 
	 * @return
	 * 
	 */
	@Override
	public List<CouponDTO> getUnExpiredCoupon() {
		
		List<Coupon> coupons =couponRepository.findByCouponDate();
		List<CouponDTO> couponDtos = new ArrayList<>();
		for(Coupon coupon: coupons) {	    
			couponDtos.add(convertToDto(coupon));
		}
		LOGGER.info(CommonConstants.CouponData);
		return couponDtos;
	}

	
	 /**
	 *
     * Method returns coupon based on eligibility for the user
     * @param couponId
     * @return
     * 
     */
	@Override
	public boolean checkEligiblity(int couponId) {

		Coupon coupon = couponRepository.findById(couponId).get();
		 List<CourierDetails> courierDetails= courierDetailsRepository.findByCouponId(coupon.getCouponId());	
		    int amountUsed=0;
		    for(CourierDetails courierDetailsAmount : courierDetails ) {
		    	amountUsed+=courierDetailsAmount.getDiscountAmount();
		    }
		List<CourierDetails> couponEligiblity= courierDetailsRepository.findByCouponIdAndUserId(couponId,JwtTokenUtil.getUserFromSecurityContext().getId());
		
		if(couponEligiblity.size() < coupon.getMaxRedeembleCountPerUser() && coupon.getRole().getName().equals(JwtTokenUtil.getUserFromSecurityContext().getRole().getName()) && coupon.getMaxRedeembleAmount() > amountUsed && coupon.getCouponExpiryDate().after(new Date())) {
			return true;
		}
		else {
			return false;
		}
	}

}
