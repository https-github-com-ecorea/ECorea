package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.ecorea.service.*;

import lombok.AllArgsConstructor;

import com.project.ecorea.dto.PageDto;

@Controller
@AllArgsConstructor
public class ProductMvcController {
	
	private ProductService productService;
	
	/* 상품 목록 화면 */
	@GetMapping("/product/productList")
	public ModelAndView productList(@RequestParam(defaultValue="1") Integer pageno, String catecode) {
		PageDto page = productService.productList(pageno, catecode);
		return new ModelAndView("product/productList").addObject("page", page);
	}

	/* 상품 상세 페이지 화면 */
	@GetMapping("/product/member/productDetail")
	public ModelAndView productRead(Integer pno) {
		return new ModelAndView("product/member/productDetail").addObject("product", productService.productRead(pno));
	}
	
	/* 문의 작성 버튼 */
	@GetMapping("/product/member/qnaUpload")
	public void qnaUpload() {
	}
	
}
