package com.example.swiftcourier.controller;



import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.commonconstants.SCExceptionConstants;
import com.example.swiftcourier.dao.BlockedToken;
import com.example.swiftcourier.handler.SuccessResponseHandler;
import com.example.swiftcourier.service.BlockedTokenService;
import com.example.swiftcourier.service.JwtTokenUtil;

import io.jsonwebtoken.Claims;


/** 
 * 
 * Handles  rest API end points with respect to logout
 *
 */

@RestController
public class LogoutController {
	
	/**
	 * 
	 *  Service to access and save all tokens via  BlockedTokenRepository
	 * 
	 */
	
	@Autowired
	BlockedTokenService tokenService;
	
	@Autowired 
	JwtTokenUtil JwtTokenUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);
	
	 
	/**
	 *  logout from application
	 * @param request
	 * @return
	 */
	@GetMapping(value="/logout/user")
	@RolesAllowed({"ROLE_MANAGER", "ROLE_OFFICEBOY"})
	public ResponseEntity<Object> logout(HttpServletRequest request){	
		try {
			String header = request.getHeader("Authorization");
			String token = header.split(" ")[1].trim();
			BlockedToken blockedtoken = new BlockedToken();
			blockedtoken.setTocken(token);
			Claims claims = JwtTokenUtil.parseClaims(token);
			blockedtoken.setTokenExpiryDate(claims.getExpiration());
			tokenService.addToken(blockedtoken);
			return SuccessResponseHandler.generateResponse(CommonConstants.Logout, HttpStatus.OK);
		}
		catch(Exception e) {
			LOGGER.error(CommonConstants.Error+" "+e);
			return SuccessResponseHandler.generateResponse(SCExceptionConstants.SC003, HttpStatus.BAD_REQUEST);
		}
		
	}
}
