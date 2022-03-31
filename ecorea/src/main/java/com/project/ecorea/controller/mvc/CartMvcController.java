package com.project.ecorea.controller.mvc;


import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class CartMvcController {
	private CartService cartService;
	
	
	// 장바구니 보기
	@GetMapping("/order/cart")
	public ModelAndView readCart() {
		String memberId = "zzzzuny";
		ModelAndView mav = new ModelAndView("order/cart");
		List<CartDto.CartList> cartList = cartService.readCart(memberId);
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	
	// 장바구니에서 상품 한 개 삭제
	@PostMapping("/order/cart/deleteOne")
	public String deleteCartProduct(Integer pno) {
		String memberId = "zzzzuny";
		cartService.deleteOne(memberId, pno);
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 상품 전체 삭제
	@PostMapping("/order/cart/deleteAll")
	public String deleteAllCartProduct(String confirm) {
		String memberId = "zzzzuny";
		if(confirm.equals("confirm")) {
			cartService.deleteAll(memberId);
		}		
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 선택상품 삭제
	@PostMapping("/order/cart/deleteSelected")
	public String deleteSelectedCartProduct(String confirm, CartDto.DeleteSelected dto) {
		String memberId = "zzzzuny";
		if(confirm.equals("confirm")) {
			cartService.deleteSelected(memberId, dto);
		}
		return "redirect:/order/cart";
	}
}
