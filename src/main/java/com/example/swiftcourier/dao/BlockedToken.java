package com.example.swiftcourier.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlockedToken {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
    String tocken; 
    Date tokenExpiryDate;
    
   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BlockedToken() {
    	
    }
	public BlockedToken(Integer id, String tocken, Date logOutDate) {
		super();
		this.id = id;
		this.tocken = tocken;
		this.tokenExpiryDate = logOutDate;
	}
	public String getTocken() {
		return tocken;
	}
	public void setTocken(String tocken) {
		this.tocken = tocken;
	}
	public Date getTokenExpiryDate() {
		return tokenExpiryDate;
	}
	public void setTokenExpiryDate(Date tokenExpiryDate) {
		this.tokenExpiryDate = tokenExpiryDate;
	}
	
	
    
}
