package com.example.swiftcourier.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity 
@Table(name="coupon")
public class Coupon {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int couponId;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;
	
	
	@ManyToMany
	@JoinTable(
		name = "coupon_user",
		joinColumns = @JoinColumn(name = "coupon_id",nullable = false),
		inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false)
	)
	private Set<User> user = new HashSet<>();
	
	
	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}


	private Date couponIssueDate;
	
	private Date couponExpiryDate;
	 
	private String discountType;
	
	private int percentage; 
	
	private int maxRedeembleCount; 
	
	private int maxRedeembleCountPerUser;
	
	private int couponUsedCount;
	
	private int maxRedeembleAmount;
	
	private int maxReedembleAmountPerUsage;




	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
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

	public int getCouponUsedCount() {
		return couponUsedCount;
	}

	public void setCouponUsedCount(int couponUsedCount) {
		this.couponUsedCount = couponUsedCount;
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
    
	public Coupon() {
		
	}
	public Coupon(int id, Role role, Set<User> user, Date couponIssueDate, Date couponExpiryDate, String discountType,
			int percentage, int maxRedeembleCount, int maxRedeembleCountPerUser, int couponUsedCount,
			int maxRedeembleAmount, int maxReedembleAmountPerUsage) {
		super();
		this.couponId = id;
		this.role = role;
		this.user = user;
		this.couponIssueDate = couponIssueDate;
		this.couponExpiryDate = couponExpiryDate;
		this.discountType = discountType;
		this.percentage = percentage;
		this.maxRedeembleCount = maxRedeembleCount;
		this.maxRedeembleCountPerUser = maxRedeembleCountPerUser;
		this.couponUsedCount = couponUsedCount;
		this.maxRedeembleAmount = maxRedeembleAmount;
		this.maxReedembleAmountPerUsage = maxReedembleAmountPerUsage;
	}
	
	
	

		
	
	
}
