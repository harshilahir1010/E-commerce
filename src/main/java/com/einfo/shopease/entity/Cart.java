package com.einfo.shopease.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="cart_table")
@Table(name="cart_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	
	@Id
	@GeneratedValue
	@Column(name="cart_id")
	private int cartId;
	
	@OneToMany
	@JoinColumn(name="pid")
	private Set<Product> products = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name="uid")
	private User user;
	
	@Column(name="quantity")
	private String totalQuantity;
	
	@Column(name="total_cost")
	private String totalCost;
	
	@Column(name="user_address")
	private String userAddress;
	
	
}
