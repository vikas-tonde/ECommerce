package com.quadBlogs.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quadBlogs.ecommerce.entity.Cart;
import com.quadBlogs.ecommerce.models.AddProductRequest;
import com.quadBlogs.ecommerce.service.CartService;
import com.quadBlogs.ecommerce.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping(value = "/admin")
public class AdminController {
	private ProductService productService;
	private CartService cartService;
	private ObjectMapper mapper;
	
	@PostMapping(value="/products",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> addProduct(@RequestPart("request") String request, @RequestPart("image") MultipartFile file ){
		if(!file.getContentType().equals("image/jpeg" )&& !file.getContentType().equals("image/png")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image should be in JPEG or PNG format");
		}
		AddProductRequest productRequest=null;
		try {
			productRequest= mapper.readValue(request, AddProductRequest.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		productRequest.setImage(file);
		try {
			productService.createProduct(productRequest);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Unable to add product");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Prodcut Added Successfully");
	}
	
	@GetMapping(value = "/carts")
	public ResponseEntity<?> getCarts(){
		List<Cart> carts = cartService.getAllPendingCarts();
		if(carts==null || carts.size()==0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No cart pending");
		return ResponseEntity.status(HttpStatus.OK).body(carts);
	}
}
