package com.project.ecorea.dto;

import java.util.List;

import lombok.Data;

@Data
public class PagingQnaDto {

	/* 문의 리스트 */
	List<QnaDto.QuestionDto> list;
	
	/* 페이지 정보 */
	PageMakerDto pageInfo;
	
}
