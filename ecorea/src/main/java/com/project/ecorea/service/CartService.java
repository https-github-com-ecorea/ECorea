package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@AllArgsConstructor
@Service
public class CartService {
	
	private CartDao cartDao;
	
	// 장바구니 목록 출력
	public List<CartDto.CartList> readCart(String memberId) {
		List<CartDto.CartList> cartList = cartDao.findByMemberId(memberId);
		
		return cartList;
	}
	
	// 상품 갯수 증가
	public Integer plusCnt(String memberId, Integer pno) {
		Cart cart = new Cart(memberId, pno, 1, null, null);
		Integer plusResult = cartDao.updateCntPlus(cart);
		return plusResult;
	}
	
	// 상품 갯수 감소
	public Integer minusCnt(String memberId, Integer pno) {
		Cart cart = new Cart(memberId, pno, 1, null, null);
		Integer minusResult = cartDao.updateCntMinus(cart);
		return minusResult;
	}
	
	// 상품 한 개 삭제
	public Integer deleteOne(String memberId, Integer pno) {
		Integer deleteOneResult = cartDao.deleteOne(memberId, pno);
		return deleteOneResult;
	}
	
	// 상품 전체 삭제
	public Integer deleteAll(String memberId) {
		Integer deleteAllResult = cartDao.deleteAll(memberId);
		return deleteAllResult;
	}
	
	// 선택상품 삭제
	public Integer deleteSelected(String memberId, CartDto.DeleteSelected dto) {
		Integer deleteSelectedResult = cartDao.deleteSelected(memberId, dto.getPnos());
		return deleteSelectedResult;		
	}
}
