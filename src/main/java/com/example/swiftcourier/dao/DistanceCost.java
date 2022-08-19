package com.example.swiftcourier.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DistanceCost {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long distanceStart;
    private long distanceEnd;
    private int cost;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getDistanceStart() {
		return distanceStart;
	}
	public void setDistanceStart(long distanceStart) {
		this.distanceStart = distanceStart;
	}
	public long getDistanceEnd() {
		return distanceEnd;
	}
	public void setDistanceEnd(long distanceEnd) {
		this.distanceEnd = distanceEnd;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
