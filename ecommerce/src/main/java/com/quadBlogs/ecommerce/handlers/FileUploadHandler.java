package com.quadBlogs.ecommerce.handlers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHandler {
	public final String UPLOAD_DIR="E:\\Java Full Stack\\uploads";
	
	public String uploadFile(MultipartFile file) {
		String path=null;
		try {
			path=UPLOAD_DIR+File.separator+file.getOriginalFilename();
			Files.copy(file.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		}catch (Exception e) {
			path=null;
			e.printStackTrace();
		}
		return path;
	}
}
