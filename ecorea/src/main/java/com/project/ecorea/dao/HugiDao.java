package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.Criteria;
import com.project.ecorea.dto.HugiDto;
import com.project.ecorea.dto.HugiDto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface HugiDao {
	
	/* 후기 목록 출력 */
	public List<HugiDto.HugiList> findByPno(Integer pno);
	
	/* 상품 상세 : 후기 개수 */
	public int getProductDetailTotal(Integer pno);
	
	/* 상품 상세 : 후기 목록 (페이징) */
	public List<HugiDto.HugiList> productDetailHugiFindbyPno(Criteria cri);

	public List<HugiDto.HugiList> findByhwriter(String loginId);

	public void deleteByHno(Integer hno);

	public void reviewSave(Hugi hugi);

	public void updateByReview(Hugi hugi);

	public Hugi findByHno(Integer hno);
	
}
