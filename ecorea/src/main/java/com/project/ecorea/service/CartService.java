package com.project.ecorea.service;


import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class CartService {
	@Value("${upload.image.path}")
	private String imagePath;
	
	private final CartDao cartDao;
	private final ProductDao productDao;
	
	// 장바구니 목록 출력
	public List<CartDto.CartList> readCart(String memberId) {
		return cartDao.findByMemberId(memberId, imagePath);
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
		return cartDao.deleteOne(memberId, pno);
	}
	
	// 상품 전체 삭제
	public Integer deleteAll(String memberId) {
		return cartDao.deleteAll(memberId);
	}
	
	// 선택상품 삭제
	public Integer deleteSelected(String memberId, CartDto.DeleteSelected dto) {
		List<Integer> list = dto.getPnos();
		list.removeAll(Collections.singleton(null));
		return cartDao.deleteSelected(memberId, list);		
	}
}
