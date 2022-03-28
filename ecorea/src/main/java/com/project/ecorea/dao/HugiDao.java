package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.HugiDto;

@Mapper
public interface HugiDao {
	
	/* 후기 목록 출력 */
	public List<HugiDto.HugiList> findByPno(Integer pno);
	
}
