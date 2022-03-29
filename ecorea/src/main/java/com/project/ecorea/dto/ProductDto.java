package com.project.ecorea.dto;

import java.util.*;

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
	public static class productRead {
		private String catecode; /* 상품 카테고리 */
		private Integer pno; /* 상품 번호 */
		private String cname; /* 제조사 */
		private String pthumbnail; /* 썸네일 */
		private String pname; /* 상품명 */
		private String pcontent; /* 상품 설명 */
		private Integer price; /* 가격 */
		private List<HugiDto.HugiList> Hugis; /* 후기 리스트 */
		private List<QnaDto.QnaList> Qnas; /* 문의 리스트 */
	}
	
}
