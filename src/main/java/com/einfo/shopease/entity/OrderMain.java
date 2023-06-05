package com.einfo.shopease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "order_main")
@Table(name = "order_main")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private int orderId;
	
	@Column(name = "buyer_address")
	private String buyerAddress;
	
	@Column(name = "buyer_email")
	private String buyerEmail;
	
	@Column(name = "buyer_phone")
	private String buyerPhone;
	
	@OneToOne
	@JoinColumn(name = "cartId")
	private Cart cart;
	
	@Column(name="orderStatus")
	private int orderStatus;

	
}
