package com.quadBlogs.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quadBlogs.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
