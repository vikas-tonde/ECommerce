package com.quadBlogs.ecommerce.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.quadBlogs.ecommerce.entity.Product;
import com.quadBlogs.ecommerce.handlers.FileUploadHandler;
import com.quadBlogs.ecommerce.models.AddProductRequest;
import com.quadBlogs.ecommerce.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService{
	private ModelMapper modelMapper;
	private FileUploadHandler fileUploadHandler;
	private ProductRepository productRepository;
	public Product createProduct(AddProductRequest addProductRequest)throws Exception {
		String path=fileUploadHandler.uploadFile(addProductRequest.getImage());
		if(path==null) {
			return null;
		}
		Product product = modelMapper.map(addProductRequest, Product.class);
		product.setImage(path);
		return productRepository.save(product);
	}
	
	public List<Product> getProdcuts(){
		List<Product> products=productRepository.findAll();
		return products;
	}
}
