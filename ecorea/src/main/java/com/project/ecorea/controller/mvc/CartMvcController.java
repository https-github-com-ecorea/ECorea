package com.project.ecorea.controller.mvc;


import java.security.*;
import java.util.*;

import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@RequiredArgsConstructor
@Controller
@Secured("ROLE_MEMBER")
public class CartMvcController {
	private final CartService cartService;
	private final ProductService productService;
	
	/* 장바구니에 담기 */
	@PostMapping("/order/cart/add")
	public String add(Integer pno, Integer count, Principal principal) {
		Boolean result = productService.shoppingCartMultiple(pno, count, principal.getName());
		if (result == false)
			return "redirect:/product/productList?page=1&amount=9&catecode=&sort=";
		return "redirect:/order/cart";
	}
	
	// 장바구니 보기
	@GetMapping("/order/cart")
	public ModelAndView readCart(Principal principal) {
		ModelAndView mav = new ModelAndView("order/cart");
		List<CartDto.CartList> cartList = cartService.readCart(principal.getName());
		mav.addObject("cartList", cartList);
		return mav;
	}
	
	// 장바구니에서 상품 한 개 삭제
	@PostMapping("/order/cart/deleteOne")
	public String deleteCartProduct(Integer pno, Principal principal) {
		cartService.deleteOne(principal.getName(), pno);
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 상품 전체 삭제
	@PostMapping("/order/cart/deleteAll")
	public String deleteAllCartProduct(String confirm, Principal principal) {
		if(confirm.equals("confirm")) {
			cartService.deleteAll(principal.getName());
		}		
		return "redirect:/order/cart";
	}
	
	// 장바구니에서 선택상품 삭제
	@PostMapping("/order/cart/deleteSelected")
	public String deleteSelectedCartProduct(String confirm, CartDto.DeleteSelected dto, Principal principal) {
		if(confirm.equals("confirm")) {
			cartService.deleteSelected(principal.getName(), dto);
		}
		return "redirect:/order/cart";
	}
}
