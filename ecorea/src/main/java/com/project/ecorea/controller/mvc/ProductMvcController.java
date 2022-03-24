package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class ProductMvcController {
	private ProductService productService;
	
	@GetMapping("/product/productUpload")
	public void productUpload() {
	}
	
	@PostMapping("/product/productUpload")
	public String productUpload(ProductDto.ProductUpload uploadDto) {
		System.out.println("==================================");
		System.out.println("###controller start###");
		System.out.println("==================================");
		productService.productUpload(uploadDto);
		return "redirect:/mypage/corp/productList";
	}
	
}
