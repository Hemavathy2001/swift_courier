package com.example.swiftcourier.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeightCost {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long startGrams;
    private long endGrams;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public long getStartGrams() {
		return startGrams;
	}
	public void setStartGrams(long startGrams) {
		this.startGrams = startGrams;
	}
	public long getEndGrams() {
		return endGrams;
	}
	public void setEndGrams(long endGrams) {
		this.endGrams = endGrams;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	private int cost;
}
