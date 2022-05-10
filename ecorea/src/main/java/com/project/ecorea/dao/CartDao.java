package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface CartDao {
	// 장바구니에 담겨있는 상품 한 개
	public Cart findByMemberIdAndPno(String memberId, Integer pno); 
	
	// 장바구니에 담겨있는 전체 상품
	public List<CartDto.CartList> findByMemberId(String memberId, String imagePath);
	
	// 장바구니 상품 수량 증가
	public Integer updateCntPlus(Cart cart);
	
	// 장바구니 상품 수량 감소
	public Integer updateCntMinus(Cart cart);
	
	// 장바구니 상품 한 개 삭제
	public Integer deleteOne(String memberId, Integer pno);
	
	// 장바구니 선택한 상품 삭제
	public Integer deleteSelected(String memberId, List<Integer> pnos);
	
	// 장바구니 전체 삭제
	public Integer deleteAll(String membeId);
	
	// 장바구니에 상품 하나 담기
	public void saveOneProduct(Cart cart);

}
