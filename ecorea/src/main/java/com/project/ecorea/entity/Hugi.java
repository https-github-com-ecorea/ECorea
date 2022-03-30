package com.project.ecorea.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class Hugi {
	
	private Integer pno; /* 상품 번호 */
	private Integer hno; /* 후기 번호 */
	private Integer jno; /* 주문 번호 */
	private String htitle; /* 제목 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate hregday; /* 등록일 */
	private String hwriter; /* 작성자 */
	private String hcontent; /* 내용 */
	private String himg; /* 이미지 */
	
}
