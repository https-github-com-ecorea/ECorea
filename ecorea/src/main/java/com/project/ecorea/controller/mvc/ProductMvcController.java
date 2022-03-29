package com.project.ecorea.controller.mvc;

import org.springframework.web.bind.annotation.*;
import com.project.ecorea.service.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

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
