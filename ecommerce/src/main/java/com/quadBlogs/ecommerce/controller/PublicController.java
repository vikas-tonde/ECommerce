package com.quadBlogs.ecommerce.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quadBlogs.ecommerce.config.CustomUserDetailsService;
import com.quadBlogs.ecommerce.config.JwtUtil;
import com.quadBlogs.ecommerce.entity.User;
import com.quadBlogs.ecommerce.models.AuthenticationRequest;
import com.quadBlogs.ecommerce.models.AuthenticationResponse;
import com.quadBlogs.ecommerce.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(allowedHeaders = "*",origins = "*")
@RequestMapping(value = "/public")
public class PublicController {
	private AuthenticationManager authenticationManager;
	private CustomUserDetailsService customUserDetailsService;
	private JwtUtil jwtTokenUtil;
	private UserService userService;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}
		final UserDetails user = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		System.out.println(user.getUsername());
		final String jwt = jwtTokenUtil.generateToken(user);
		return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getUsername(), user.getAuthorities().stream()
				.map(grantedAuthority -> grantedAuthority.toString()).collect(Collectors.joining())));
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user){
		try {
			User response=userService.createUser(user);
			response.setPassword(null);
			return new ResponseEntity<User>(response, HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<Object>("User registration failed", HttpStatus.FAILED_DEPENDENCY);
		}
	}
}
