package com.project.ecorea.dto;

import java.util.List;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDto {
	
	/* 상품 목록 출력용 */
	@Data
	public static class productList {
		private Integer pno; /* 상품 번호 */
		private String cname; /* 제조사 */
		private String pthumbnail; /* 썸네일 */
		private String pname; /* 상품명 */
		private Integer price; /* 가격 */
	}
	
	@Data
	@AllArgsConstructor
	@Builder
	public static class productRead {
		private String pcategory; /* 상품 카테고리 */
		private Integer pno; /* 상품 번호 */
		private String cname; /* 제조사 */
		private String pthumbnail; /* 썸네일 */
		private String pname; /* 상품명 */
		private String pcontent; /* 상품 설명 */
		private Integer price; /* 가격 */
		private List<Hugi> Hugis; /* 후기 리스트 */
		private List<QnaQ> Qnaqs; /* 문의 리스트 - 질문 */
		private List<QnaA> Qnaas; /* 문의 리스트 - 답변 */
	}
	
}
