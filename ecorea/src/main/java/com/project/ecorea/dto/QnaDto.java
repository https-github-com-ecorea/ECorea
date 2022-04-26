package com.project.ecorea.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaDto {

	/* 문의 출력 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class QuestionDto {
		private Integer pno; /* 상품 번호 */
		private Integer qqno; /* 질문 번호 */
		private String qqcategory; /* 질문 카테고리 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String qqimg; /* 이미지 */
		private LocalDate qqregday; /* 등록일 */
		private String memberId; /* 작성자 */
		private String isAnswer; /* 답변 여부 */
	}
	
	/* 문의 답변 출력 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class AnswerDto {
		private Integer qqno; /* 질문 번호 */
		private Integer qano; /* 답변 번호 */
		private String corpId; /* 답변 작성자 (기업) */
		private String qacontent; /* 답변 내용 */
		
		public QnaA toEntity() {
			return QnaA.builder().qqno(qqno).qano(qano).corpId(corpId).qacontent(qacontent).build();
		}
	}

	/* 문의 작성 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class uploadQuestion {
		private Integer pno; /* 상품 번호 */
		private String qqcategory; /* 질문 카테고리 */
		private MultipartFile qqimg; /* 이미지 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String memberId; /* 작성자 */
		
		public QnaQ toEntity() {
			return QnaQ.builder().pno(pno).qqcategory(qqcategory)
					.qqtitle(qqtitle).qqcontent(qqcontent).qqregday(LocalDate.now()).memberId(memberId).build();
		}
	}
	
	/* 문의 수정 */
	@Data 
	@AllArgsConstructor
	@NoArgsConstructor
	public static class updateQuestion {
		private Integer qqno; /* 문의 번호 */
		private MultipartFile qqimg; /* 이미지 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String memberId; /* 작성자 */
		
		public QnaQ toEntity() {
			return QnaQ.builder().qqno(qqno).qqtitle(qqtitle).qqcontent(qqcontent).memberId(memberId).build();
		}
	}

}
