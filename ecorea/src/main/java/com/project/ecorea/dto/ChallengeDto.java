package com.project.ecorea.dto;

import java.time.*;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChallengeDto {
  
	/* 기업 회원 : 챌린지 등록 */
	@Data
	public static class ChallengeUpload {
		private String cname; /* 챌린지 이름 */
		private Integer cgoal; /* 달성 목표 */
		private Integer cpoint; /* 보상 포인트 */
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate cregday; /* 등록일 */
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate cstartday; /* 챌린지 시작일 */
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate cendday; /* 챌린지 마감일 */
		private MultipartFile cthumbnail; /* 썸네일 */
		private String ccontent; /* 내용 */
		
		public Challenge toEntity() {
			return Challenge.builder().cname(cname).cgoal(cgoal).cpoint(cpoint).cregday(LocalDate.now())
					.cstartday(cstartday).cendday(cendday).ccontent(ccontent).cjoincnt(0).build();
		}
	}
	
	@Data
	@Builder
	public static class ChallengeDetail {
		private Integer cno; /* 챌린지 글 번호 */
		private String cname; /* 챌린지 이름 */
		private Integer cgoal; /* 목표 */
		private LocalDate cstartday; /* 챌린지 시작 기간 */
		private LocalDate cendday; /* 챌린지 마감 기간 */
		private String cthumbnail; /* 썸네일 이미지 */
		private String ccontent; /* 챌린지 상세 내용 */
		private Integer cjoincnt; /* 인증 인원 */
		private Integer applycnt; /* 참여 인원 */
		// private List<ChProveDto.ChallengeDetailProveList> chProveList; /* 챌린지 인증 글 리스트 */
	}
	
	@Data
	@Builder
	public static class ChallengeList {
		private String corpname;
		private Integer cno; /* 챌린지 글 번호 */
		private String cname; /* 챌린지 이름 */
		private LocalDate cregday; /* 등록일 */
		private LocalDate cstartday; /* 챌린지 시작 기간 */
		private LocalDate cendday; /* 챌린지 마감 기간 */
		private String cthumbnail; /* 썸네일 이미지 */
		private Integer cgoal; /* 목표 */
		private Integer cjoincnt; /* 참여 인원 */
	}
  
}
