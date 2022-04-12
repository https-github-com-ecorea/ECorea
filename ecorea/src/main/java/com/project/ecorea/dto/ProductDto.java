package com.project.ecorea.dto;

import java.time.LocalDate;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.dto.QnaDto.QuestionDto;
import com.project.ecorea.entity.*;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {

	/* 상품 목록 출력용 */
	@Data
	@AllArgsConstructor
	public static class productList {
		private String catecode; /* 상품 카테고리 */
		private Integer pno; /* 상품 번호 */
		private String cname; /* 제조사 */
		private String pthumbnail; /* 썸네일 */
		private String pname; /* 상품명 */
		private Integer price; /* 가격 */
	}
	
	/* 상품 상세 페이지 출력용 */
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ProductRead {
		private String catecode; /* 상품 카테고리 */
		private Integer pno; /* 상품 번호 */
		private String cname; /* 제조사 */
		private String pthumbnail; /* 썸네일 */
		private String pname; /* 상품명 */
		private String pcontent; /* 상품 설명 */
		private Integer price; /* 가격 */
		private List<HugiDto.HugiList> Hugis; /* 후기 리스트 */
		private List<QnaDto.QuestionDto> QQnas; /* 문의 리스트 */
		private List<QnaDto.AnswerDto> AQnas; /* 문의 리스트 */
	}


	@Data
	public static class Upload {
		private String pname;
		private String corpId;
		private Integer price;
		private Integer pstock;
		private MultipartFile pthumnail;
		private String pcontent;
		
		public Product toEntity() {
			return Product.builder().catecode("1").price(price).pname(pname).pstock(pstock)
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
