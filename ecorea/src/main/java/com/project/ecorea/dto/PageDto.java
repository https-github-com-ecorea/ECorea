package com.project.ecorea.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
public class PageDto {
	
	private Integer prev; /* 이전 페이지로 */
	private Integer start; /* 시작 페이지 */
	private Integer end; /* 마지막 페이지 */
	private Integer next; /* 다음 페이지로 */
	
	private Integer pageno; /* 페이지 번호 */
	private List<ProductDto.productList> products; /* 상품 목록 */

}
