package com.example.swiftcourier.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swiftcourier.commonconstants.CommonConstants;
import com.example.swiftcourier.dao.BlockedToken;
import com.example.swiftcourier.repository.BlockedTokenRepository;

/**
 * 
 * @author hemavathy.ravichandr
 * Includes Service methods for add token to database and deleting token from data base 
 */
@Service
public class BlockedTokenServiceImpl implements BlockedTokenService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BlockedTokenServiceImpl.class);
	
	/**
	 * To add and access blocked tokens via blockedTokenRepository
	 */
	@Autowired 
	BlockedTokenRepository  blockedTokenRepository;
	
    /**
     * Method to add token to database
     */
	@Override
	public void addToken(BlockedToken blockedtoken) {
		LOGGER.info(CommonConstants.blockedToken);
		blockedTokenRepository.save(blockedtoken);
	}
    
	
	/**
	 * Method to delete token from database
	 */
	@Override
	public void deleteToken() {
		LOGGER.info(CommonConstants.delete);
	    blockedTokenRepository.deleteByTokenExpiryDate();	
	}
	
}
