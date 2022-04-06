package com.project.ecorea.entity;

import java.time.*;

import org.springframework.format.annotation.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.dto.ChallengeDto.*;

import lombok.*;
import lombok.experimental.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Challenge {
	/* 챌린지 글 번호 */
	private Integer cno;
	/* 챌린지 등록 기업 이름 */
	private String cname;
	/* 챌린지 목표 */
	private Integer cgoal;
	/* 챌린지 보상 포인트 */
	private Integer cpoint;
	/* 챌린지 글 작성일 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cregday;
	/* 챌린지 시작 기간 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cstartday;
	/* 챌린지 마감 기간 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cendday;
	/* 챌린지 썸네일 이미지 */
	private String cthumbnail;
	/* 챌린지 상세 내용 */
	private String ccontent;
	/* 챌린지 참여 인원 */
	private Integer cjoincnt;
	/* 챌린지 등록 기업 아이디 */
	private String corpId;
	
	public ChallengeDto.ChallengeDetail toDetailDto() {
		return ChallengeDto.ChallengeDetail.builder().cno(cno).cname(cname).cgoal(cgoal).cstartday(cstartday).cendday(cendday).cthumbnail(cthumbnail).ccontent(ccontent).cjoincnt(cjoincnt).build();
	}
	
	public ChallengeDto.ChallengeList toListDto() {
		return ChallengeDto.ChallengeList.builder().cno(cno).cname(cname).cstartday(cstartday).cendday(cendday).cthumbnail(cthumbnail).cregday(cregday).build();
	}
}
