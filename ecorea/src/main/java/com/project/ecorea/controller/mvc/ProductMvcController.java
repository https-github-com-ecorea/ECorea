package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;

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

	// 등록된 상품 리스트 보기
	@GetMapping("/mypage/corp/productList")
	public ModelAndView regProductList() {
		String corpId = "samsung";
		return new ModelAndView("mypage/corp/productList").addObject("regProducts", productService.regProductsList(corpId));
	}
	
	// 상품 수정페이지에서 등록된 상품 상세정보 출력
	@GetMapping("/product/corp/productUpdate")
	public ModelAndView readCorpProductDetail() {
		String corpId = "samsung";
		Integer pno = 1;
		return new ModelAndView("product/productUpdate").addObject("product", productService.readProductDetailForUpdate(corpId, pno));
	}
	
	// 상품 수정
	@PostMapping("/product/corp/productUpdate")
	public String updateProduct(ProductDto.UpdateProduct updateDto, RedirectAttributes ra) {
		if((updateDto.getPname()==null || updateDto.getPname()=="") && (updateDto.getPrice()==null) && (updateDto.getPstock()==null) && (updateDto.getPthumbnail()==null || updateDto.getPthumbnail()=="") && (updateDto.getPcontent()==null || updateDto.getPcontent()=="")) {
			ra.addFlashAttribute("변경할 값이 없습니다.");
			return "redirect:/product/corp/productUpdate";
		}
		else {
			productService.updateProduct(updateDto);
			return "redirect:/product/corp/productUpdate";
		}				
	}
	
	// 등록된 상품 삭제
	@PostMapping("/product/corp/productDelete")
	public String deleteProduct(String corpId, Integer pno) {
		productService.deleteProduct(corpId, pno);
		return "redirect:/mypage/corp/productList";
	}
	
}