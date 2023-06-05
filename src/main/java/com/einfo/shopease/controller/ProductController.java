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

import com.einfo.shopease.entity.Product;
import com.einfo.shopease.repo.CategoryRepo;
import com.einfo.shopease.repo.ProductRepo;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepo repo;
	
	@Autowired
	CategoryRepo rp;
	
	@GetMapping("product/view")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<Product> getProduct(){
		return repo.findAll();
	}
	
	
	@GetMapping("product/view/{pid}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Product> getSpecificCategory(@PathVariable int pid) throws Exception{
		Optional<Product> product=repo.findById(pid);
		if(product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(null).of(product);
		}
		else {
			 throw new Exception("Item Not Found!!!!");
		}
	}
	
	
	@GetMapping("findByCategory/{cid}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public List<Product> getProductByCid(@PathVariable int cid){
		return repo.findByCidCid(cid);
	}
	
	
	@PostMapping("product/add")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SELLER')")
	public ResponseEntity<String> postProduct(@RequestBody Product product){
		String a="";
		String type=product.getProductName();
		String desc=product.getProductDescription();
		String price=product.getProductPrice();
		if(type != a && desc != a && price != a) {
		
			repo.save(product);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	
	
	@PutMapping("product/edit")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_SELLER')")
	public ResponseEntity<String> addOrUpdateProduct(@RequestBody Product product){
		String a="";
		String type=product.getProductName();
		String desc=product.getProductDescription();
		String price=product.getProductPrice();
		if(type != a && desc != a && price != a) {
		
			repo.save(product);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	

	@DeleteMapping("product/delete/{pid}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable int pid){
		Optional<Product> product=repo.findById(pid);
		if(product.isPresent()) {
			Product product1=repo.getOne(pid);
		   repo.delete(product1);
		   return ResponseEntity.status(HttpStatus.OK).body("Data with id "+pid+" deleted sucessfully");
		}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data available at " +pid+" this Id");
		}
	}
	
	
	@GetMapping("product/verifiedProduct")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public List<Product> getVerifiedProducts(){
		return repo.getVerifiedProducts();
	}
	
	
	@GetMapping("product/verifiedProduct/{productName}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<List<Product>> getVerifiedProduct(@PathVariable String productName) throws Exception{
		Optional<List<Product>> product=Optional.ofNullable(repo.findByProductName(productName));
		if(product.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(null).of(product);
		}
		else {
			 throw new Exception("Item Not Found!!!!");
		}
	}
	
	@GetMapping("product/unVerifiedProduct")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<Product> getUnVerifiedProducts(){
		return repo.getUnVerifiedProducts();
	}
	
	@GetMapping("product/updateToVerified/{pid}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public  ResponseEntity<String> getApprovedProducts(@PathVariable int pid) {
		Optional<Product> product=repo.findById(pid);
		if(product.isPresent()) {
			repo.updateProductToVerifiedStatus(pid);
			return ResponseEntity.status(HttpStatus.OK).body("Product is Verified");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is not verified.Add Product Id properly");
			}		
	}

	
}
