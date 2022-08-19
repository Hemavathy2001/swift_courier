package com.example.swiftcourier.dto;

import java.util.Date;

import com.example.swiftcourier.dao.Role;

public class CouponDTO {
    private int id;
    private Role role;
    private Date couponIssueDate;
    private Date couponExpiryDate;
    private String discountType;
    private int percentage;
    private int maxRedeembleCount;
    private int maxRedeembleCountPerUser;
    private int maxRedeembleAmount;
    private int maxReedembleAmountPerUsage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getCouponIssueDate() {
		return couponIssueDate;
	}
	public void setCouponIssueDate(Date couponIssueDate) {
		this.couponIssueDate = couponIssueDate;
	}
	public Date getCouponExpiryDate() {
		return couponExpiryDate;
	}
	public void setCouponExpiryDate(Date couponExpiryDate) {
		this.couponExpiryDate = couponExpiryDate;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getMaxRedeembleCount() {
		return maxRedeembleCount;
	}
	public void setMaxRedeembleCount(int maxRedeembleCount) {
		this.maxRedeembleCount = maxRedeembleCount;
	}
	public int getMaxRedeembleCountPerUser() {
		return maxRedeembleCountPerUser;
	}
	public void setMaxRedeembleCountPerUser(int maxRedeembleCountPerUser) {
		this.maxRedeembleCountPerUser = maxRedeembleCountPerUser;
	}
	public int getMaxRedeembleAmount() {
		return maxRedeembleAmount;
	}
	public void setMaxRedeembleAmount(int maxRedeembleAmount) {
		this.maxRedeembleAmount = maxRedeembleAmount;
	}
	public int getMaxReedembleAmountPerUsage() {
		return maxReedembleAmountPerUsage;
	}
	public void setMaxReedembleAmountPerUsage(int maxReedembleAmountPerUsage) {
		this.maxReedembleAmountPerUsage = maxReedembleAmountPerUsage;
	}
	
}
