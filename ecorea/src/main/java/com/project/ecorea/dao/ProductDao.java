package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface ProductDao {	
	// 기업회원 - 상품 등록
	public void save(Product product);
	
	// 기업회원 - 등록된 상품 목록 출력
	public List<ProductDto.corpProductList> findByCorpId(String corpId);

}
