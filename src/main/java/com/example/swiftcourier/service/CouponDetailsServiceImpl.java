package com.example.swiftcourier.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.controller.ConsignmentBookingController;
import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.dao.Role;
import com.example.swiftcourier.dto.CouponDTO;
import com.example.swiftcourier.repository.CouponRepository;
import com.example.swiftcourier.repository.CourierDetailsRepository;
import com.example.swiftcourier.repository.UserRepository;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods accessing coupon details from data base
 *
 */
@Service
public class CouponDetailsServiceImpl implements CouponDetailsService{
	
	
	/**
	 * To access coupon details via couponRepository
	 */
	@Autowired 
	CouponRepository couponRepository;
	
	/**
	 * 
	 * To access User details via userRepository
	 */
	@Autowired 
	UserRepository  userRepository;
	
	/**
	 * 
	 * To access courier details via courierDetailsRepository
	 * 
	 */
	@Autowired
	CourierDetailsRepository courierDetailsRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CouponDetailsServiceImpl.class);
		
	
	ModelMapper modelMapper = new ModelMapper();
	
	public CouponDTO convertToDto(Coupon coupon) {
		CouponDTO couponDto = modelMapper.map(coupon, CouponDTO.class);
		return couponDto;
	}
    
	/**
	 * 
	 * Method returns coupon that is used maximum number of times
	 * 
	 */
	@Override
	public List<CouponDTO> redemptionTimes() {
		List<Coupon> couponList = couponRepository.findAll();
		int var = couponList.stream().map(e->e.getCouponUsedCount()).max(Comparator.comparing(a->a)).get();
		List<Coupon> coupons = couponList.stream().filter(e -> e.getCouponUsedCount()==var).toList();
		List<CouponDTO> couponDtos = new ArrayList<>(); 
		for(Coupon coupon: coupons) {
			couponDtos.add(convertToDto(coupon));
		}
		LOGGER.info(CommonConstants.CouponData);
		return couponDtos;
	}
	
	
    
	/**
	 * 
	 * Method returns coupon  based on maximum redemption amount
	 * 
	 */
	@Override
	public List<CouponDTO> redemptionAmount() {
		List<CourierDetails> courierDetails = courierDetailsRepository.findAll();
		
		
		Map<Integer , List<CourierDetails>> couponData = courierDetails.stream().collect(Collectors.groupingBy(CourierDetails::getCouponId));
		    System.out.println(couponData.toString());
		List<List<CourierDetails>> value = couponData.entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());
			
		Map<Integer,Double> sumValues = new HashMap<>();
		for(List<CourierDetails> valu : value) {
			Double sum=0.0;
			Integer v=0; 
			for (CourierDetails val : valu) {
				sum+=val.getDiscountAmount();
				v=val.getCouponId();
			}
			sumValues.put(v, sum);			
			
		}		
		Double maximumValues=sumValues.entrySet().stream().map(Map.Entry::getValue).max(Comparator.comparing(a -> a)).get();
		List<Integer> maxCoupon = sumValues.entrySet().stream().filter(e -> e.getValue().equals(maximumValues)).map(Map.Entry::getKey).collect(Collectors.toList());
		List<CouponDTO> couponDtos = new ArrayList<>(); 
		for(Integer i : maxCoupon) {
			Coupon coupon = couponRepository.findById(i).get();
			couponDtos.add(convertToDto(coupon));
		}
		LOGGER.info(CommonConstants.CouponData);
		return couponDtos;
	}
    
	/**
	 * 
	 * Method returns total worth of coupons used
	 * 
	 */
	@Override
	public double couponTotalWorth() {
		List<CourierDetails> courierDetail = courierDetailsRepository.findAll();
		List<Double> totalWorth = (List<Double>) courierDetail.stream().map(CourierDetails::getDiscountAmount).collect(Collectors.toList());
		Double value = totalWorth.stream().reduce(0.0, (a,b) -> a+b);
		return value;
	}
    
	
	/**
	 * 
	 * Method returns coupon which is used maximum number of times based on role
	 * 
	 */
	@Override
	public String maxredemptionByType() {
		List<Coupon> coupons = couponRepository.findAll();
		Map<String , List<Coupon>> coupon = coupons.stream().collect(Collectors.groupingBy(Coupon::getDiscountType));
		
		List<List<Coupon>> value = coupon.entrySet().stream().map(a -> a.getValue()).collect(Collectors.toList());
		Map<String,Integer> sumValues = new HashMap<>();
		for(List<Coupon> valu : value) {
			Integer sum=0;
			String v = null; 
			for (Coupon val : valu) {
				sum+=val.getCouponUsedCount();
				v=val.getDiscountType();
			}
			sumValues.put(v,sum);			
		}	
	    int max= sumValues.entrySet().stream().map(Map.Entry::getValue).max(Comparator.comparing(a -> a)).get();
	    List<String> maxcoupon = sumValues.entrySet().stream().filter(e -> e.getValue().equals(max)).map(Map.Entry::getKey).collect(Collectors.toList());
		return maxcoupon.toString();
		
	}
	
}


