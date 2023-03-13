package com.quadBlogs.ecommerce.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quadBlogs.ecommerce.config.CustomUserDetails;
import com.quadBlogs.ecommerce.entity.Address;
import com.quadBlogs.ecommerce.entity.Cart;
import com.quadBlogs.ecommerce.entity.PurchaseProduct;
import com.quadBlogs.ecommerce.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping(value = "/customer")
public class CustomerController {
	private CartService cartService;

	@PostMapping(value = "/cart/add")
	public ResponseEntity<?> addToCart(@RequestBody Map<String, String> values,
			Authentication auth) {
		PurchaseProduct product= new PurchaseProduct();
		product.setQuantity(Integer.parseInt(values.get("quantity")));
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		Cart cart=cartService.addToCart(product, Long.parseLong(values.get("pid")), userDetails.getUsername());
		if(cart==null)
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Unable to add product to cart");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(cart);
	}
	
	@PostMapping(value = "/cart/purchase")
	public ResponseEntity<?> purchaseCart(@Valid @RequestBody Address address, Authentication auth){
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		Cart cart = cartService.purchaseCart(address, userDetails.getUsername());
		if(cart==null)
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Your cart is not purchasable");
		return ResponseEntity.status(HttpStatus.OK)
				.body(cart);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<?> getCart(Authentication auth){
		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		Cart cart = cartService.getCart(userDetails.getUsername());
		if(cart==null)
			return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("Your cart is empty");
		return ResponseEntity.status(HttpStatus.OK)
				.body(cart);
	}

}
