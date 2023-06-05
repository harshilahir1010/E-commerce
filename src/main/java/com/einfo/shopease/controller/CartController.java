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

import com.einfo.shopease.entity.Cart;
import com.einfo.shopease.entity.Product;
import com.einfo.shopease.repo.AddCartRepo;

@RestController
public class CartController {
	
	@Autowired
	AddCartRepo repo;
	
	@GetMapping("addcart/view")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<Cart> getCart(){
		return repo.findAll();
	}
	@GetMapping("addcart/view/{cartId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Cart> getSpecificCategory(@PathVariable int cartId) throws Exception{
		Optional<Cart> cart=repo.findById(cartId);
		if(cart.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(null).of(cart);
		}
		else {
			 throw new Exception("Item Not Found!!!!");
		}
		
	}
	
	@PostMapping("addcart/add")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> postCart(@RequestBody Cart cart){
		String a="";
		String add=cart.getUserAddress();
		String quantity=cart.getTotalQuantity();
		String price=cart.getTotalCost();
		if(add != a && quantity != a && price != a) {
		
			repo.save(cart);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	@PutMapping("addcart/edit")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> putCart(@RequestBody Cart cart){
		String a="";
		String add=cart.getUserAddress();
		String quantity=cart.getTotalQuantity();
		String price=cart.getTotalCost();
		if(add != a && quantity != a && price != a) {
		
			repo.save(cart);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	@DeleteMapping("addcart/delete/{cartId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<String> deleteCart(@PathVariable int cartId){
		
		Optional<Cart> cart=repo.findById(cartId);
		if(cart.isPresent()) {
			Cart cart1=repo.getOne(cartId);
		   repo.delete(cart1);
		   return ResponseEntity.status(HttpStatus.OK).body("Data with id "+cartId+" deleted sucessfully");
		}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data available at " +cartId+" this Id");
		}
	}
	
	
}
