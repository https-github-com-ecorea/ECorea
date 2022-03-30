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
	private ProductDao productDao;
	
	// 장바구니 목록 출력
	public List<CartDto.CartList> readCart(String memberId) {
		List<CartDto.CartList> cartList = cartDao.findByMemberId(memberId);
		
		return cartList;
	}
	
	// 상품 갯수 증가
	public Integer plusCnt(String memberId, Integer pno) {
		Product product = productDao.findByPno(pno);
		Cart cart = cartDao.findByMemberIdAndPno(memberId, pno);
		if(cart.getCartcnt()+1>product.getPstock()) {
			return -1;
		}
		cartDao.updateCntPlus(new Cart(memberId, pno, 1, null, null));
		return cart.getCartcnt()+1;
	}
	
	// 상품 갯수 감소
	public Integer minusCnt(String memberId, Integer pno) {
		Cart cart = cartDao.findByMemberIdAndPno(memberId, pno);
		if(cart.getCartcnt()<=1) {
			return -1;
		}
		cartDao.updateCntMinus(new Cart(memberId, pno, 1, null, null));
		return cart.getCartcnt()-1;
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
