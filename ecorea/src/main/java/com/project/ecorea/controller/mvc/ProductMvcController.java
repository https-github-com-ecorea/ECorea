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
	private HugiService hugiService;
	private QnaService qnaService;
	
	/* 상품 목록 화면 */
	@GetMapping("/product/productList")
	public ModelAndView productList(@RequestParam(defaultValue="1") Integer pageno, String catecode) {
		PageDto page = productService.productList(pageno, catecode);
		return new ModelAndView("product/list").addObject(page);
	}

	/* 상품 상세 페이지 화면 */
	@GetMapping("/product/member/productDetail")
	public ModelAndView productRead(Integer pno) {
		return new ModelAndView("product/member/productDetail").addObject("product", productService.productRead(pno))
				.addObject("hugi", hugiService.hugiList(pno)).addObject("qna", qnaService.qnaList(pno));
	}
	
	/* 문의 작성 버튼 */
	@GetMapping("/product/member/qnaUpload")
	public void qnaUpload() {
	}
	
}
