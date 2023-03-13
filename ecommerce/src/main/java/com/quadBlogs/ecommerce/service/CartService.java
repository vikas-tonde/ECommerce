package com.quadBlogs.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quadBlogs.ecommerce.entity.Address;
import com.quadBlogs.ecommerce.entity.Cart;
import com.quadBlogs.ecommerce.entity.Product;
import com.quadBlogs.ecommerce.entity.PurchaseProduct;
import com.quadBlogs.ecommerce.entity.User;
import com.quadBlogs.ecommerce.repository.CartRepository;
import com.quadBlogs.ecommerce.repository.ProductRepository;
import com.quadBlogs.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartService {
	private CartRepository cartRepository;
	private UserRepository userRepository;
	private ProductRepository productRepository;
	
	@Transactional
	public Cart addToCart(PurchaseProduct purchaseProduct,long pid,String email) {
		User user= userRepository.findByEmail(email);
		Product product = productRepository.findById(pid).get();
		if(purchaseProduct.getQuantity()>product.getQuantity()) {
			return null;
		}
//		product.setQuantity(product.getQuantity()-purchaseProduct.getQuantity());
		purchaseProduct.setProduct(product);
		Cart cart= cartRepository.findCartByStatusAndUser(true,user);
		if(cart == null) {
			cart= new Cart();
			cart.setUser(user);
			cart.setStatus(true);
			cart.setSubtotal(0);
			cart.setApproval("created");
			cart.setPurchasable(true);
		}
		cart.setSubtotal(cart.getSubtotal()+(product.getPrice()*purchaseProduct.getQuantity()));
		cart.setPurchaseProducts(purchaseProduct);
		return cartRepository.save(cart);
	}
	
	@Transactional
	public Cart purchaseCart(Address address, String email) {
		User user= userRepository.findByEmail(email);
		Cart cart= cartRepository.findCartByStatusAndUser(true,user);
		if(cart!=null) {
			cart.getPurchaseProducts().stream().forEach(t -> {
				if(t.getProduct().getQuantity()<t.getQuantity()) {
					t.setPurchasable(false);
				}
			});
			boolean isPurchasable = cart.getPurchaseProducts().stream().anyMatch(t -> 
				t.isPurchasable()==false
			);
			cart.setPurchasable(!isPurchasable);
			
		}
		if(!cart.isPurchasable()) {
			cart=null;
		}
		else {
			cart.setApproval("pending");
			cart.setAddress(address);
		}
		cartRepository.save(cart);
		return cart;
		
	}
	
	public Cart getCart(String email) {
		User user= userRepository.findByEmail(email);
		Cart cart= cartRepository.findCartByStatusAndUser(true,user);
		return cart;
	}
	
	public List<Cart> getAllPendingCarts(){
		List<Cart> carts = cartRepository.findCartByApproval("pending");
		return carts;
	}
}
