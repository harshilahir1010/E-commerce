package com.einfo.shopease.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.shopease.entity.Category;
import com.einfo.shopease.entity.Product;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
