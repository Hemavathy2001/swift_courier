package com.example.swiftcourier.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
	
	@Entity
	@Table(name = "roles")
	public class Role {
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(nullable = false, length = 50, unique = true)
		private String name;
		
		@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Coupon> coupon  = new ArrayList<>();

		public Role() { }
		
		public Role(String name) {
			this.name = name;
		}
		
		public Role(Integer id) {
			super();
			this.id = id;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return this.name;
		}
		
		
	}

