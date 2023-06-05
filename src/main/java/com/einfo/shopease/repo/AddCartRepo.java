package com.einfo.shopease.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfo.shopease.entity.Cart;

public interface AddCartRepo extends JpaRepository<Cart, Integer> {

}
