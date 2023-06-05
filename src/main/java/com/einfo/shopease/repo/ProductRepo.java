package com.einfo.shopease.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.einfo.shopease.entity.Category;
import com.einfo.shopease.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	List<Product> findByCidCid(int cid);  //product for particular category
	
	@Query("SELECT p FROM product_table p WHERE p.isAproved=1")  //all products which are verified
	List<Product> getVerifiedProducts();
	
	@Query("SELECT p FROM product_table p WHERE p.isAproved=0")  //all products which are verified
	List<Product> getUnVerifiedProducts();
	
	List<Product> findByProductName(String productName);
	
	@Modifying
    @Transactional
    @Query("UPDATE product_table p SET p.isAproved = 1 WHERE p.pid = :productId")
    void updateProductToVerifiedStatus(int productId);
	
}
