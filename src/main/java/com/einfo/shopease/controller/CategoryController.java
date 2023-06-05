package com.einfo.shopease.controller;

import java.util.List;
//import java.util.Optional;
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
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.einfo.shopease.entity.Category;
import com.einfo.shopease.repo.CategoryRepo;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepo repo;
	
	@GetMapping("category/view")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public List<Category> getCategory(){
		return repo.findAll();
	}
	@GetMapping("category/view/{cid}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public ResponseEntity<Category> getSpecificCategory(@PathVariable int cid) throws Exception{
		Optional<Category> category=repo.findById(cid);
		if(category.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(null).of(category);
		}
		else {
			 throw new Exception("Item Not Found!!!!");
		}	
	}
	
	@PostMapping("category/add")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> postCategory(@RequestBody Category category){
		String a="";
		String type=category.getCategoryType();
		String desc=category.getCategoryDesc();
		if(type != a && desc != a) {
		
			repo.save(category);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	@PutMapping("category/edit")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> putCategory(@RequestBody Category category){
		String a="";
		String type=category.getCategoryType();
		String desc=category.getCategoryDesc();
		if(type != a && desc != a) {
		
			repo.save(category);
			return ResponseEntity.status(HttpStatus.OK).body("Data is added sucessfully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is not added.Add all data properly");
			}
	}
	
	@DeleteMapping("category/delete/{cid}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable int cid){
		
		Optional<Category> category=repo.findById(cid);
		if(category.isPresent()) {
		   Category category1=repo.getOne(cid);
		   repo.delete(category1);
		   return ResponseEntity.status(HttpStatus.OK).body("Data with id "+cid+" deleted sucessfully");
		}else {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no data available at " +cid+" Id");
		}
	}
}
	
	/*
	@PostMapping("category/add")
	public CategoryVo postCategory(@RequestBody CategoryVo categoryVo){
		repo.save(categoryVo);
		return categoryVo;
	}
	@DeleteMapping("category/delete/{cid}")
	public String deleteCategory(@PathVariable int cid) {
		
		CategoryVo c=repo.getOne(cid);
		repo.delete(c);
		return "deleted";
	}

*/
