package com.quadBlogs.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quadBlogs.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping(value = "/common")
public class CommonController {

	private ProductService productService;
	
	@GetMapping(value = "/products")
	public ResponseEntity<?> getProdcuts(){
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProdcuts());
	}
}
