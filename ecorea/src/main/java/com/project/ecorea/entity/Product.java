package com.project.ecorea.entity;

import java.time.*;

import com.project.ecorea.dto.*;

import lombok.*;
import lombok.experimental.*;

import org.springframework.format.annotation.*;

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
  @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate pregday; /* 등록일 */
	private String corpId; /* 제조사 */
	
}
