package com.quadBlogs.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quadBlogs.ecommerce.entity.Cart;
import com.quadBlogs.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findCartByStatusAndUser(boolean status, User user);
	List<Cart> findCartByApproval(String approval);
}
