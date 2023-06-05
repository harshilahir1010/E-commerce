package com.einfo.shopease.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.einfo.shopease.entity.Category;
import com.einfo.shopease.entity.OrderMain;
import com.einfo.shopease.entity.Product;
import com.einfo.shopease.repo.OrderMainRepo;

@RestController
public class OrderMainController {
	
	@Autowired
	OrderMainRepo repo;
	
	@GetMapping("order/view")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SELLER')")
	public List<OrderMain> getAllOrder(){
		return repo.findAll();
		
	}
	
	@GetMapping("order/view/{orderId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SELLER')")
	public ResponseEntity<OrderMain> getSpecificOrder(@PathVariable int orderId) throws Exception{
		Optional<OrderMain> orderMain=repo.findById(orderId);
		if(orderMain.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(null).of(orderMain);
		}
		else {
			 throw new Exception("Order Not Found!!!!");
		}
	}
	
	@PostMapping("order/add")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> postOrder(@RequestBody OrderMain orderMain){
		String a="";
		String add=orderMain.getBuyerAddress();
		String mail=orderMain.getBuyerEmail();
		String num=orderMain.getBuyerPhone();
		if(add != a && mail != a && num != a) {
		
			repo.save(orderMain);
			return ResponseEntity.status(HttpStatus.OK).body("Order send sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order will not process sucessfully please enter buyer address , contact number and email");
			}
	}
	@PutMapping("order/edit")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> putOrder(@RequestBody OrderMain orderMain){
		String a="";
		String add=orderMain.getBuyerAddress();
		String mail=orderMain.getBuyerEmail();
		String num=orderMain.getBuyerPhone();
		if(add != a && mail != a && num != a) {
		
			repo.save(orderMain);
			return ResponseEntity.status(HttpStatus.OK).body("Edited sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please enter buyer address , contact number and email");
			}
	}
	
	@DeleteMapping("order/delete/{orderId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> deleteCategory(@PathVariable int orderId){
		//System.out.println(orderId);
		Optional<OrderMain> orderMain=repo.findById(orderId);
		if(orderMain.isPresent()) {
			OrderMain orderMain1=repo.getOne(orderId);
		   repo.delete(orderMain1);
		   return ResponseEntity.status(HttpStatus.OK).body("Order Deleted Sucessfully");
		}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no order available at " +orderId+" Id");
		}
	}
	
	
	@GetMapping("order/openOrder")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<OrderMain> getopenOrder(){
		return repo.getOpenStatus();
	}
	@GetMapping("order/processOrder")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<OrderMain> getprocessOrder(){
		return repo.getProcessStatus();
	}
	@GetMapping("order/inTransmitOrder")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<OrderMain> getinTransmitOrder(){
		return repo.getInTransmitStatus();
	}
	@GetMapping("order/deliveredOrder")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<OrderMain> getdeliveredOrder(){
		return repo.getDiliveredStatus();
	}
	
	
	@GetMapping("order/updateToProcess/{orderId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public  ResponseEntity<String> getApprovedProducts(@PathVariable int orderId) {
		Optional<OrderMain> orderMain1=repo.findById(orderId);
		if(orderMain1.isPresent()) {
			repo.updateOpenToProcessStatus(orderId);
			return ResponseEntity.status(HttpStatus.OK).body("Updated status from Open to Process");
		}
				else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not verified.Add Order Id properly");
			}		
	}
	@GetMapping("order/updateInTrasmit/{orderId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public  ResponseEntity<String> getupdateInTrasmit(@PathVariable int orderId) {
		Optional<OrderMain> orderMain1=repo.findById(orderId);
		if(orderMain1.isPresent()) {
			repo.updateProcessToInTransmitStatus(orderId);
			return ResponseEntity.status(HttpStatus.OK).body("Updated status from Process to InTrasmit");
		}
				else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not verified.Add Order Id properly");
			}		
	}
	@GetMapping("order/updateDelivered/{orderId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public  ResponseEntity<String> getupdateDelivered(@PathVariable int orderId) {
		Optional<OrderMain> orderMain1=repo.findById(orderId);
		if(orderMain1.isPresent()) {
			repo.updateInTransmitToDiliveredStatus(orderId);
			return ResponseEntity.status(HttpStatus.OK).body("Updated status from InTrasmit to Delivered");
		}
				else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id is not verified.Add Order Id properly");
			}		
	}
	

}
