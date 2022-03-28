package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class ProductMvcController {
	private ProductService productService;
	
	// 상품등록 페이지
	@GetMapping("/product/productUpload")
	public void uploadProduct() {
	}
	
	// 상품등록
	@PostMapping("/product/productUpload")
	public String uploadProduct(ProductDto.Upload uploadDto) {
		productService.uploadProduct(uploadDto);
		return "redirect:/mypage/corp/productList";
	}

	// 등록된 상품 보기
	@GetMapping("/mypage/corp/productList")
	public ModelAndView regProductList() {
		String corpId = "samsung";
		return new ModelAndView("mypage/corp/productList").addObject("regProducts", productService.regProductsList(corpId));
	}
}