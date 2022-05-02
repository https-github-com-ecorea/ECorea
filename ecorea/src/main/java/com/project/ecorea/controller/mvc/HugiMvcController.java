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
public class HugiMvcController {
	private HugiService hugiService;

	// 일반회원 후기 목록 페이지
	@GetMapping("/order/reviewList")
	public ModelAndView reviewList(Principal principal, Criteria cri) {
		List<HugiDto.HugiList> hugiList = hugiService.memberHugiList(principal.getName(), cri);
		return new ModelAndView("order/reviewList").addObject("hugi", hugiList).addObject("pageMaker", new PageMakerDto(cri, hugiService.getHugiListTotal(principal.getName())));
	}
	
	// 일반회원 후기 등록 페이지
	@GetMapping("/order/reviewUpload")
	public ModelAndView reviewUpload(Integer jno) {
		return new ModelAndView("order/reviewUpload").addObject("jno", jno);
	}
	
	// 일반회원 후기 등록
	@PostMapping("/order/reviewUpload")
	public String reviewUpload(Principal principal, HugiDto.HugiUpload upload) {
		hugiService.reviewUpload(principal.getName(), upload);
		return "redirect:/order/reviewList";
	}
	
	// 일반 회원 후기 수정 페이지
	@GetMapping("/order/reviewUpdate")
	public ModelAndView reviewUpdate(Principal principal, Integer hno) {
		return new ModelAndView("order/reviewUpdate").addObject("hugi", hugiService.readReviewUpdate(principal.getName(), hno));
	}
	
	// 후기 수정
	@PostMapping("/order/reviewUpdate")
	public String reviewUpdate(Integer hno, HugiDto.HugiUpdate update, Principal principal) {
		hugiService.reviewUpdate(hno, update);
		
		return "redirect:/order/reviewList";
	}
	
	// 후기 삭제
	@PostMapping("/order/reviewDelete")
	public String reviewDelete(Integer hno) {
		hugiService.reviewDelete(hno);
		
		return "redirect:/order/reviewList";
	}
	
}
