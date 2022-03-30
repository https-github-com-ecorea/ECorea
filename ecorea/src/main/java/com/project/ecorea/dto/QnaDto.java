package com.project.ecorea.dto;

import java.time.LocalDate;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaDto {

	/* 문의 출력용 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class QnaQList {
		private Integer qqno; /* 질문 번호 */
		private String qqcategory; /* 질문 카테고리 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String qqimg; /* 이미지 */
		private LocalDate qqregday; /* 등록일 */
		private String memberId; /* 작성자 */
	}
	
	/* 문의 답변 출력용 */
	@Data
	@AllArgsConstructor
	public static class QnaAList {
		private Integer qqno; /* 질문 번호 */
		private Integer qano; /* 답변 번호 */
		private String corpId; /* 답변 작성자 (기업) */
		private String qacontent; /* 답변 내용 */
	}

}
