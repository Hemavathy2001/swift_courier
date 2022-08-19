package com.example.swiftcourier.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="courier_details")
public class CourierDetails {
     
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 	 
	 @ManyToOne
	 @JsonIgnoreProperties("courier")
	 @JoinColumn(name = "user_id")
	 private User user;
	 
	 
	 private String deliveryType;
	 
	 private long deliveryTypePrice;
	 
	 private long weight ;
	 
	 private long weightPrice;
	 
	 @Column(nullable = false, length = 6)
     private int sourcePinCode;
     
	 @Column(nullable = false, length = 6)
     private int destinationPinCode;
	 
	 private long distance;
	 
	 private long distancePrice;
	 
	 private Date bookingDate;
	 
	 private int couponId;
	 
	 private double discountAmount;
	 
     private double totalAmount; 
	 
	 private boolean isPaid;
	 
	

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public long getDistance() {
		return distance;
	}

	public void setDistance(long distance) {
		this.distance = distance;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public long getDeliveryTypePrice() {
		return deliveryTypePrice;
	}

	public void setDeliveryTypePrice(long deliveryTypePrice) {
		this.deliveryTypePrice = deliveryTypePrice;
	}

	public long getWeightPrice() {
		return weightPrice;
	}

	public void setWeightPrice(long weightPrice) {
		this.weightPrice = weightPrice;
	}

	public int getSourcePinCode() {
		return sourcePinCode;
	}

	public void setSourcePinCode(int sourcePinCode) {
		this.sourcePinCode = sourcePinCode;
	}

	public int getDestinationPinCode() {
		return destinationPinCode;
	}

	public void setDestinationPinCode(int destinationPinCode) {
		this.destinationPinCode = destinationPinCode;
	}

	public long getDistancePrice() {
		return distancePrice;
	}

	public void setDistancePrice(long distancePrice) {
		this.distancePrice = distancePrice;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isPaid() {
		return isPaid;
	}

	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	
	 
	 
	     
     
}
