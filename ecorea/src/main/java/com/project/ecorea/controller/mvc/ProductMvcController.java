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
	
	private ProductService service;
	
	/* 상품 목록 화면 */
	@GetMapping("/product/list")
	public ModelAndView productList(@RequestParam(defaultValue="1") Integer pageno) {
		PageDto page = service.productList(pageno);
		return new ModelAndView("product/list").addObject(page);
	}

	/* 상품 상세 페이지 화면 */
	@GetMapping("/product/read")
	public ModelAndView productRead(Integer pno) {
		return new ModelAndView("product/read").addObject("product", service.productRead(pno));
	}
}
