package com.example.swiftcourier.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.swiftcourier.dao.AuthRequest;
import com.example.swiftcourier.dao.AuthResponse;
import com.example.swiftcourier.dao.User;
import com.example.swiftcourier.service.JwtTokenUtil;

/**
 * 
 * 
 * Handles all rest API end points with respect to login
 *
 */

@RestController
public class AuthController {
	
	@Autowired AuthenticationManager authManager;	
	@Autowired JwtTokenUtil jwtUtil;
	
	/**
	 *  end point to login to the application
	 * @param request
	 * @return
	 */
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);	
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
}
