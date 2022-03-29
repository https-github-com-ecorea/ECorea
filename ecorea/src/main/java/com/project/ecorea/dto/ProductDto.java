package com.project.ecorea.dto;

import java.time.*;

import org.springframework.web.multipart.*;

import com.project.ecorea.entity.*;

import lombok.*;

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
			return Product.builder().catecode("11").price(price).pname(pname).pstock(pstock)
					.pcontent(pcontent).pordercnt(0).pregday(LocalDate.now()).corpId(corpId).build();
		}
	}
	
	@Data
	public static class CorpProductList {
		private Integer pno;
		private String pthumbnail;
		private String pname;
		private String corpId;
	}
	
	@Data
	public static class ProductDetailForUpdate {
		private Integer pno;
		private String pname;
		private String corpId;
		private Integer price;
		private Integer pstock;
		private MultipartFile pthumbnail;
		private String pcontent;
	}
	
	@Data
	@AllArgsConstructor
	public static class UpdateProduct  {
		private Integer pno;
		private String pname;
		private Integer price;
		private Integer pstock;
		private String pthumbnail;
		private String pcontent;
		private String corpId;
	}
	
	
}
