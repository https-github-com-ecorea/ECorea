package com.project.ecorea.dto;

import java.time.*;

import org.springframework.web.multipart.*;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {
	
	@Data
	public static class ProductUpload {
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
}
