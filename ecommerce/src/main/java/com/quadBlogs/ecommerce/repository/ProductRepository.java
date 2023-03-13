package com.quadBlogs.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quadBlogs.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
