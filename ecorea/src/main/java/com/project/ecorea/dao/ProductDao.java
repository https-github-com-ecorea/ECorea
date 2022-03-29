package com.project.ecorea.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.entity.*;
import com.project.ecorea.dto.*;

@Mapper
public interface ProductDao {
	
	/* 카테고리별 상품 개수 */
	public int productCnt(String catecode);
	
	/* 페이징 */
	public List<ProductDto.productList> productListPaging(Integer firstRnum, Integer lastRnum, String imagePath, String catecode);
	
	/* 상품 상세 페이지 */
	public Product findByPno(Integer pno);
	
}
