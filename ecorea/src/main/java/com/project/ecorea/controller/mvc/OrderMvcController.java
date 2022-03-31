package com.project.ecorea.controller.mvc;

import java.security.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class OrderMvcController {
	private OrderService orderService;
	private HugiService hugiService;
	
	@PostMapping("/order/pay")
	public void cartToOrder() {
		
	}

	// 일반회원 후기 목록 페이지
	@GetMapping("/order/reviewList")
	public ModelAndView reviewList(Principal principal) {
		List<HugiDto.HugiList> hugiList = hugiService.memberHugiList(principal.getName());
		
		return new ModelAndView("order/reviewList").addObject("hugi", hugiList);
	}
	
	// 일반회원 후기 등록 페이지
	@GetMapping("/order/reviewUpload")
	public ModelAndView reviewUpload(Integer pno, Integer jno) {
		return new ModelAndView("order/reviewUpload").addObject("pno", pno).addObject("jno", jno);
	}
	
	@PostMapping("/order/reviewUpload")
	public String reviewUpload(Principal principal, HugiDto.HugiUpload upload) {
		hugiService.reviewUpload(principal.getName(), upload);
		
		return "redirect:/order/reviewList";
	}
	
	// 일반 회원 후기 수정 페이지
	@GetMapping("/order/reviewUpdate")
	public ModelAndView reviewUpdate() {
		return new ModelAndView("order/reviewUpdate");
	}
	
	@PostMapping("/order/reviewUpdate")
	public String reviewUpdate(Principal principal) {
		
		
		return "redirect:/order/reviewList";
	}
	
	@PostMapping("/order/reviewDelete")
	public String reviewDelete(Integer hno) {
		hugiService.reviewDelete(hno);
		
		return "redirect:/order/reviewList";
	}
	
}
