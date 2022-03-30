package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;

@Mapper
public interface QnaDao {
	
	/* 문의 목록 출력 */
	public List<QnaDto.QnaQList> qfindByPno(Integer pno);
	
	public List<QnaDto.QnaAList> afindByPno(Integer pno);
	
}
