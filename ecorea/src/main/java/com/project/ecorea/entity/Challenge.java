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
@Accessors(chain = true)
public class Challenge {
	private Integer cno;
	private String cname;
	private Integer cgoal;
	private Integer cpoint;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cregday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cstartday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate cendday;
	private String cthumbnail;
	private String ccontent;
	private Integer cjoincnt;
	private String corpId;
	
	public ChallengeDto.ChallengeDetail toDto() {
		return ChallengeDto.ChallengeDetail.builder().cno(cno).cname(cname).cgoal(cgoal).cstartday(ccontent).cendday(ccontent).cthumbnail(cthumbnail).ccontent(ccontent).cjoincnt(cjoincnt).build();
	}
}
