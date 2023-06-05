package com.einfo.shopease.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="category_table")
@Table(name="category_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name="category_id")
	private int cid;
	
	@Column(name="category_type")
	private String categoryType;
	
	@Column(name="category_description")
	private String categoryDesc;
	
	
}
