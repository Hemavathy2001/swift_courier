package com.example.swiftcourier.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiftcourier.dao.Coupon;
import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.dao.DistanceCost;
import com.example.swiftcourier.dao.Role;
import com.example.swiftcourier.dao.User;
import com.example.swiftcourier.dao.WeightCost;
import com.example.swiftcourier.repository.CouponRepository;
import com.example.swiftcourier.repository.CourierDetailsRepository;
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
public interface CourierService {
	
	/**
	 * Method adds Courier details to database
	 * @param courierDetails
	 */
	public void addCourier(CourierDetails courierDetails) ;

}
