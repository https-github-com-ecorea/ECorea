package com.project.ecorea.controller.mvc;

import java.security.*;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.service.*;

import lombok.*;

@AllArgsConstructor
@Controller
public class ChallengeMvcController {
	private ChallengeService challengeService;
	
	/* 전체 유저 챌린지 목록 출력 */
	@GetMapping("/challenge/challengeList")
	public ModelAndView readchallengeList() {
		return new ModelAndView().addObject("challenge", challengeService.readchallengeList());
	}
	
	@GetMapping("/challenge/corp/challengeList")
	public ModelAndView readCorpChallengeList(Principal principal) {
		return new ModelAndView("challenge/corp/challengeList").addObject("challenge", challengeService.readCorpChallengeList(principal.getName()));
	}
	
	@GetMapping("/challenge/member/challengeDetail")
	public ModelAndView readUserDetail(Integer cno) {
		return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", challengeService.readUserDetail(cno));
	}
	
	@GetMapping("challenge/corp/challengeDetail")
	public ModelAndView readcorpDetail(Integer cno) {
		return new ModelAndView("challenge/corp/challengeDetail").addObject("challenge", challengeService.readCorpDetail(cno));
	}
}
