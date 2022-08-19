package com.example.swiftcourier.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.swiftcourier.dao.BlockedToken;


/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods for add token to database and deleting token from data base  
 */
@Service
public interface BlockedTokenService {
	
	
    /**
     * This methods add token to database via Blocked token Repository
     * @param blockedtoken
     */
	public void addToken(BlockedToken blockedtoken) ;
	
	
    /**
     * 
     * This method deletes token from repository
     */
	public void deleteToken();

   
}
