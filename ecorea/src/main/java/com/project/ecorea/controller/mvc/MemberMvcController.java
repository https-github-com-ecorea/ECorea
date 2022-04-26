package com.project.ecorea.controller.mvc;

import java.security.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class MemberMvcController {
	private MyPageService mypageService;
	
	/* 마이 페이지 - 배송 주소록 관리 화면 */
	@GetMapping("/mypage/member/addressList")
	public ModelAndView addressList() {
		return new ModelAndView("mypage/member/addressList");
	}

	/* 배송 주소록 추가 팝업창 화면 */
	@GetMapping("/mypage/member/newAddress")
	public void addAddressMvc() {
	}
	
	@GetMapping("/mypage/corp/corpMypage")
	public void readCorpMypage() {
	}

	/* 마이 페이지 화면 */
	@GetMapping("/mypage/member/memberMypage")
	public ModelAndView readMypage(Principal principal) {
		return new ModelAndView("/mypage/member/memberMypage").addObject("mypage", mypageService.readMyPage(principal.getName())) ;
	}
}
