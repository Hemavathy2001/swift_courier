package com.example.swiftcourier.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.swiftcourier.service.BlockedTokenService;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enabled", matchIfMissing = true)
public class SchedulerConfig {
	
	/**
	 * Service to access tokens via database
	 */
	@Autowired 
	BlockedTokenService blockedTokenService ;
	
	
	
   /**
    * scheduler to delete tokens
    */
	@Scheduled(cron = "${cronValue}")
	public void deleteTokens() {
        	blockedTokenService.deleteToken();
	}
}
