package com.project.ecorea.controller.mvc;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

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
		return new ModelAndView("order/cart").addObject("cart", cartService.readCart(memberId));
	}
	
	
	// 장바구니에서 상품 한 개 삭제
	@PostMapping("/order/cart/deleteOne")
	public String deleteCartProduct() {
		
		return "redirect:/order/cart";
	}
	
}
