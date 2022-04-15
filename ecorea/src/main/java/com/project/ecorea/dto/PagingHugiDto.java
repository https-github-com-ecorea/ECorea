package com.project.ecorea.dto;

import java.util.List;

import lombok.Data;

@Data
public class PagingHugiDto {

	/* 후기 리스트 */
	List<HugiDto.HugiList> list;
	
	/* 페이지 정보 */
	PageMakerDto pageInfo;
	
}
