package com.quadBlogs.ecommerce.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quadBlogs.ecommerce.entity.User;
import com.quadBlogs.ecommerce.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private UserRepository userRepository;

	public User createUser(User user) throws Exception {
		PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
		String encodedPassword=passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setRole("CUSTOMER");
		return userRepository.save(user);
	}
}
