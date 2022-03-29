package com.project.ecorea.dao;

import java.util.*;

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

	// 기업회원 - 상품 등록
	public void save(Product product);
	
	// 기업회원 - 등록된 상품 목록 출력


	public List<ProductDto.CorpProductList> findByCorpId(String corpId);
	
	// 상품 수정페이지에서 등록된 상품상세 정보 보여주기
	public ProductDto.ProductDetailForUpdate findByCorpIdAndPno(String corpId, Integer pno);
	
	// 상품 정보 수정
	public Integer updateProduct(ProductDto.UpdateProduct updateProduct);
	
	// 상품 삭제
	public Integer deleteProduct(String corpId, Integer pno);

}
