package com.project.ecorea.dto;

import java.util.*;

import lombok.*;

@Data
public class PagingChProveDto {
	/* 인증 리스트 */
	List<ChProveDto.ChallengeDetailProveList> list;
	
	/* 페이지 정보 */
	PageMakerDto pageInfo;
}
