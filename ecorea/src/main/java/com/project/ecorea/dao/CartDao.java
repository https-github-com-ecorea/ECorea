package com.project.ecorea.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

@Mapper
public interface CartDao {
	
	public Cart findByMemberIdAndPno(String memberId, Integer pno); 
	
	public List<CartDto.CartList> findByMemberId(String memberId, String imagePath);
	
	public Integer updateCntPlus(Cart cart);
	
	public Integer updateCntMinus(Cart cart);
	
	public Integer deleteOne(String memberId, Integer pno);
	
	public Integer deleteSelected(String memberId, List<Integer> pnos);
	
	public Integer deleteAll(String membeId);
	
	/* 장바구니에 상품 하나 담기 */
	public void saveOneProduct(Cart cart);
	
	/* 장바구니에 상품 수량 여러 개 담기 */
	
}
