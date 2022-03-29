package com.project.ecorea.service;

import java.io.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
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

	// 상품 등록
	public void uploadProduct(ProductDto.Upload uploadDto) {
		Product product = uploadDto.toEntity();
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
	
	// 등록 상품 리스트 출력
	public List<ProductDto.CorpProductList> regProductsList(String corpId) {
		List<ProductDto.CorpProductList> corpProductListDto = productDao.findByCorpId(corpId);
		return corpProductListDto;		
	}
	
	// 등록된 상품 상세정보 출력
	public ProductDto.ProductDetailForUpdate readProductDetailForUpdate(String corpId, Integer pno) {
		ProductDto.ProductDetailForUpdate product = productDao.findByCorpIdAndPno(corpId, pno);
		return product;
	}
	
	// 상품 상세정보 수정
	public Boolean updateProduct(ProductDto.UpdateProduct productDto) {
		Integer updateResult = productDao.updateProduct(productDto);
		if(updateResult<=0) {
			return false;
		}
		return true;		
	}
	
	// 상품 삭제
	public Boolean deleteProduct(String corpId, Integer pno) {
		Integer deleteResult = productDao.deleteProduct(corpId, pno);
		if(deleteResult<=0) {
			return false;
		}
		return true;
	}

}
