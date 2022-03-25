package com.project.ecorea.entity;

import java.time.LocalDate;

import com.project.ecorea.dto.ProductDto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain=true)
public class Product {
	
	private String catecode; /* 카테고리 */
	private Integer pno; /* 상품 번호 */
	private String pname; /* 상품명 */
	private Integer price; /* 가격 */
	private Integer pstock; /* 재고 */
	private String pthumbnail; /* 썸네일 */
	private String pcontent; /* 상품 설명 */
	private Integer pordercnt; /* 주문 횟수 */
	private LocalDate pregday; /* 등록일 */
	private String corpId; /* 제조사 */
	
	public ProductDto.productRead toDto(String imagePath) {
		return ProductDto.productRead.builder().catecode(catecode).pno(pno).pcontent(pcontent).pname(pname).price(price).pthumbnail(imagePath + pthumbnail).build();
	}

}
