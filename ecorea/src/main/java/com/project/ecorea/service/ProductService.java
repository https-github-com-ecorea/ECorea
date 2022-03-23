package com.project.ecorea.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.ProductDto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class ProductService {
	@Value("${product.image.folder}")
	private String imageFolder;
	@Value("${default.image.name}")
	private String defaultImage;
	
	private final ProductDao productDao;

	public void productUpload(ProductUpload uploadDto) {
		System.out.println("==================================");
		System.out.println("### service start ###");
		System.out.println("uploadDto : " + uploadDto);
		System.out.println("==================================");
		Product product = uploadDto.toEntity();
		System.out.println("==================================");
		System.out.println("product : " + product);
		System.out.println("==================================");
		MultipartFile pthumbnail = uploadDto.getPthumnail();
		product.setPthumbnail(defaultImage);
		if(pthumbnail!=null && pthumbnail.isEmpty()==false && pthumbnail.getContentType().toLowerCase().startsWith("image/")) {
			String pthumbnailName = UUID.randomUUID() + "-" + pthumbnail.getOriginalFilename();
			File file = new File(imageFolder, pthumbnailName);
			try {
				pthumbnail.transferTo(file);
				product.setPthumbnail(pthumbnailName);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}			
		}
		productDao.save(product);

	}

}
