package com.project.ecorea.service;

import java.util.*;

import org.springframework.stereotype.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;

import lombok.*;

@AllArgsConstructor
@Service
public class CartService {
	
	private CartDao cartDao;
	
	public List<CartDto.CartList> readCart(String memberId) {
		List<CartDto.CartList> cartList = cartDao.findByMemberId(memberId);
		
		return cartList;
	}
}
