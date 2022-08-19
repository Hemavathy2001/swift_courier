package com.example.swiftcourier.controller;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.commonconstants.SCExceptionConstants;
import com.example.swiftcourier.dao.CourierDetails;
import com.example.swiftcourier.handler.SuccessResponseHandler;
import com.example.swiftcourier.service.CourierService;

/**
 * 
 * Handles all rest API end points with respect to courier 
 *
 */
@RestController
public class ConsignmentBookingController {
	
	/**
	 * 
	 * Service to access and add all Courier Details via Courier Details Repository 
	 * 
	 */
	@Autowired 
	CourierService courierService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsignmentBookingController.class);

	
	 /**
     * adding courier to database -> accessed by manager
     * @param 
     * @return
     */
	@PostMapping(value="/couriers")
	@RolesAllowed({"ROLE_MANAGER", "ROLE_OFFICEBOY"})
	public ResponseEntity<Object> addCourier(@RequestBody CourierDetails courierDetails) {
		try {
			courierService.addCourier(courierDetails);
			return SuccessResponseHandler.generateResponse(CommonConstants.CourierAdded, HttpStatus.CREATED);
		}
		catch(Exception e) {
			LOGGER.error(CommonConstants.Error+" "+e);
			return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC001, HttpStatus.BAD_REQUEST);
		}
	}
}
