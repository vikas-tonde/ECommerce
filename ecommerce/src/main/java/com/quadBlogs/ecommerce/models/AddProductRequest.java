package com.quadBlogs.ecommerce.models;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProductRequest {
	@NotEmpty
	private String productName;

	@NotEmpty
	private String description;
	
	@NotEmpty
	private String color;
	@NotEmpty
	private long quantity;
	@NotEmpty
	private double price;
	@NotEmpty
	private MultipartFile image;
}
