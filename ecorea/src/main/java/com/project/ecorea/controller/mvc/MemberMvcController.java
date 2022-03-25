package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class MemberMvcController {
	
	/* 마이 페이지 - 배송 주소록 관리 화면 */
	@GetMapping("/mypage/member/addressListMvc")
	public ModelAndView addressList() {
		return new ModelAndView("mypage/member/addressList");
	}

	/* 배송 주소록 추가 팝업창 화면 */
	@GetMapping("/mypage/member/addAddressMvc")
	public void addAddressMvc() {
	}
	
}
