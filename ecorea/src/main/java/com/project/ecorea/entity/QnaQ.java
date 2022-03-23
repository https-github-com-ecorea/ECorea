package com.project.ecorea.entity;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class QnaQ {
	
	private Integer pno; /* 상품 번호 */
	private Integer qqno; /* 질문 번호 */
	private String qqcategory; /* 질문 카테고리 */
	private String qqtitle; /* 제목 */
	private String qqcontent; /* 내용 */
	private String qqimg; /* 이미지 */
	private LocalDate qqregday; /* 등록일 */
	private String memberId; /* 작성자 */
	
}
