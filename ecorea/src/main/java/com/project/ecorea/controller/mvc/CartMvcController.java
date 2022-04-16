package com.project.ecorea.controller.mvc;


import java.util.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@RequiredArgsConstructor
@Controller
public class CartMvcController {
	private final CartService cartService;
	private final ProductService productService;
	String memberId = "zzzzuny";
	
	/* 장바구니에 담기 */
	@PostMapping("/order/cart/add")
	public String add(Integer pno, Integer count, String memberId) {
		Boolean result = productService.shoppingCartMultiple(pno, count, memberId);
		if (result == false)
			return "redirect:/product/productList?page=1&amount=9&catecode=&sort=";
		return "redirect:/order/cart";
	}
	
	// 장바구니 보기
	@GetMapping("/order/cart")
	public ModelAndView readCart() {
		ModelAndView mav = new ModelAndView("order/cart");
		List<CartDto.CartList> cartList = cartService.readCart(memberId);
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	// 장바구니에서 상품 한 개 삭제
	@PostMapping("/order/cart/deleteOne")
	public String deleteCartProduct(Integer pno) {
		cartService.deleteOne(memberId, pno);
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 상품 전체 삭제
	@PostMapping("/order/cart/deleteAll")
	public String deleteAllCartProduct(String confirm) {
		if(confirm.equals("confirm")) {
			cartService.deleteAll(memberId);
		}		
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 선택상품 삭제
	@PostMapping("/order/cart/deleteSelected")
	public String deleteSelectedCartProduct(String confirm, CartDto.DeleteSelected dto) {
		if(confirm.equals("confirm")) {
			cartService.deleteSelected(memberId, dto);
		}
		return "redirect:/order/cart";
	}
}
