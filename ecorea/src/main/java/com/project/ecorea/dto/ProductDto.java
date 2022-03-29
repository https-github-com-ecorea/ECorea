package com.project.ecorea.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.entity.Product;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {
	
	@Data
	public static class Upload {
		private String pname;
		private String corpId;
		private Integer price;
		private Integer pstock;
		private MultipartFile pthumnail;
		private String pcontent;
		
		public Product toEntity() {
			return Product.builder().pcategory("11").price(price).pname(pname).pstock(pstock)
					.pcontent(pcontent).pordercnt(0).pregday(LocalDate.now()).corpId(corpId).build();
		}
	}
	
	@Data
	public static class corpProductList {
		private Integer pno;
		private String pthumbnail;
		private String pname;
		private String corpId;
	}
	
}
