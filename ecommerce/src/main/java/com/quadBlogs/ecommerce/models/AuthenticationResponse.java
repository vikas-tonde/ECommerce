package com.quadBlogs.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
	private String jwt;
	private String email;
	private String role;
}
