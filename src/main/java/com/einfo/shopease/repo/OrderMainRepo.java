package com.einfo.shopease.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.einfo.shopease.entity.OrderMain;
import com.einfo.shopease.entity.Product;

import jakarta.transaction.Transactional;

public interface OrderMainRepo extends JpaRepository<OrderMain, Integer>{
	
	@Query("SELECT p FROM order_main p WHERE p.orderStatus=0")  //all products which are verified
	List<OrderMain> getOpenStatus();
	
	@Query("SELECT p FROM order_main p WHERE p.orderStatus=1")  //all products which are verified
	List<OrderMain> getProcessStatus();
	
	
	@Query("SELECT p FROM order_main p WHERE p.orderStatus=2")  //all products which are verified
	List<OrderMain> getInTransmitStatus();
	
	@Query("SELECT p FROM order_main p WHERE p.orderStatus=3")  //all products which are verified
	List<OrderMain> getDiliveredStatus();
	
	
	@Modifying
    @Transactional
    @Query("UPDATE order_main p SET p.orderStatus = 1 WHERE p.orderId = :orderId")
    void updateOpenToProcessStatus(int orderId);
	

	@Modifying
    @Transactional
    @Query("UPDATE order_main p SET p.orderStatus = 2 WHERE p.orderId = :orderId")
    void updateProcessToInTransmitStatus(int orderId);
	

	@Modifying
    @Transactional
    @Query("UPDATE order_main p SET p.orderStatus = 3 WHERE p.orderId = :orderId")
    void updateInTransmitToDiliveredStatus(int orderId);
	

}
