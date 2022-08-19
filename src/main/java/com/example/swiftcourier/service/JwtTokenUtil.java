package com.example.swiftcourier.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.swiftcourier.dao.Role;
import com.example.swiftcourier.dao.User;
import com.example.swiftcourier.repository.BlockedTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	
	@Autowired 
	BlockedTokenRepository blockedTokenRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
	
	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	// This method generates access token 
	public String generateAccessToken(User user) {
		
		return Jwts.builder()
				.setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
				.claim("role", user.getRole().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	
	//This method validates access token
	public boolean validateAccessToken(String token) {
		try {
			if(blockedTokenRepository.findByTocken(token) == null) {
				Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
				return true;
			}
		} catch (ExpiredJwtException ex) {
			LOGGER.error("JWT expired", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
		} catch (MalformedJwtException ex) {
			LOGGER.error("JWT is invalid", ex);
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("JWT is not supported", ex);
		} catch (SignatureException ex) {
			LOGGER.error("Signature validation failed");
		}
		
		return false;
	}
	
	
	//Method to get user from security context
	public User getUserFromSecurityContext()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		User users =  (User) authentication.getPrincipal();
		return users;
	}
	
	// method to get user role from security context
	public Role getUserRoleFromSecurityContext()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();	
		User users =  (User) authentication.getPrincipal();
		return users.getRole();
	}
	
	// method to get subject from token 
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
}
