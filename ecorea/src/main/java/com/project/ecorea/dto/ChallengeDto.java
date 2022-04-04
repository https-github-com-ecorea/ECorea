package com.project.ecorea.dto;

import java.time.*;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.entity.*;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChallengeDto {
  
	/* 기업 회원 : 챌린지 등록 */
	@Data
	public static class challengeUpload {
		private String cname; /* 기업명 */
		private Integer cgoal; /* 달성 목표 */
		private Integer cpoint; /* 보상 포인트 */
		private LocalDate cregday; /* 등록일 */
		private LocalDate cstartday; /* 챌린지 시작일 */
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
		private Integer cno;
		private String cname;
		private Integer cgoal;
		private String cstartday;
		private String cendday;
		private String cthumbnail;
		private String ccontent;
		private Integer cjoincnt;
		private Integer applycnt;
	}
  
}
