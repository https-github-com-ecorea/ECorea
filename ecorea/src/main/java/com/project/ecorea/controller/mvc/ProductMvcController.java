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
	public void uploadProduct() {
	}
	
	@PostMapping("/product/productUpload")
	public String uploadProduct(ProductDto.Upload uploadDto) {
		productService.uploadProduct(uploadDto);
		return "redirect:/mypage/corp/productList";
	}
	
}
