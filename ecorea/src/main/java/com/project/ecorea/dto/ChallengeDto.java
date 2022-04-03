package com.project.ecorea.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChallengeDto {
	
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
