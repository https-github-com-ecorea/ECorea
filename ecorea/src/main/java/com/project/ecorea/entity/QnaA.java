package com.project.ecorea.entity;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class QnaA {

	private Integer pno; /* 상품 번호 */
	private Integer qqno; /* 질문 번호 */
	private Integer qano; /* 답변 번호 */
	private String corpId; /* 답변 작성자 (기업) */
	private String qacontent; /* 답변 내용 */
	
}
