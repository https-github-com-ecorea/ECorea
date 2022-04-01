package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class ChProveMvcController {
	private ChProveService proveService;
	
	// 나의 챌린지 목록 보기
	@GetMapping("/challenge/member/challengeList")
	public ModelAndView readProve() {
		String memberId = "zzzzuny";
		ModelAndView mav = new ModelAndView("challenge/member/challengeList");
		mav.addObject("proveList", proveService.readProve(memberId));
		return mav;
	}
}
