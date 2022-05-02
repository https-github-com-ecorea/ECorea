package com.project.ecorea.controller.mvc;

import java.security.*;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class MemberMvcController {
	private MyPageService mypageService;
	
	/* 마이 페이지 - 배송 주소록 관리 화면 */
	@Secured("ROLE_MEMBER")
	@GetMapping("/mypage/member/addressList")
	public ModelAndView addressList() {
		return new ModelAndView("mypage/member/addressList").addObject("role", "ROLE_MEMBER");
	}

	/* 배송 주소록 추가 팝업창 화면 */
	@GetMapping("/mypage/member/newAddress")
	public void addAddressMvc() {
	}
	
	/* 기업 회원 마이 페이지 화면 */
	@Secured("ROLE_CORP")
	@GetMapping("/mypage/corp/corpMypage")
	public void readCorpMypage(Model model) {
		model.addAttribute("role", "ROLE_CORP");
	}

	/* 일반 회원 마이 페이지 화면 */
	@Secured("ROLE_MEMBER")
	@GetMapping("/mypage/member/memberMypage")
	public ModelAndView readMypage(Principal principal) {
		return new ModelAndView("/mypage/member/memberMypage").addObject("mypage", mypageService.readMyPage(principal.getName())).addObject("role", "ROLE_MEMBER");
	}
}
