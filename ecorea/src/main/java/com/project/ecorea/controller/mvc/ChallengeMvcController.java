package com.project.ecorea.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.security.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dao.*;
import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class ChallengeMvcController {
	
	private ChallengeService service;
	
	/* 기업 회원 : 챌린지 등록 화면 */
	@Secured("ROLE_CORP")
	@GetMapping("/challenge/corp/challengeUpload")
	public void challengeUpload() {
	}
	
	/* 기업 회원 : 챌린지 등록 */
	@Secured("ROLE_CORP")
	@PostMapping("/challenge/corp/challengeUpload")
	public String challengeUpload(ChallengeDto.ChallengeUpload challenge, Principal principal) {
		service.challengeUpload(challenge, principal.getName());
		return "redirect:/challenge/corp/challengeList";
	}
		
	/* 기업 회원 : 챌린지 수정 가능한 지 확인 */
	@Secured("ROLE_CORP")
	@GetMapping("/challenge/corp/challengeUpdate")
	public ModelAndView challengeUpdateisPossible(ModelAndView mav, Integer cno) {
		Challenge challenge = service.challengeUpdateView(cno);
		Boolean result = service.challengeUpdateisDate(challenge);
		if (result == true) {
			mav.addObject("challenge", service.challengeUpdateView(cno));
		} else {
			mav.addObject("data", new Message("수정이 가능한 날짜가 아닙니다.", "/challenge/corp/challengeList"));
			mav.setViewName("alert");
		}
		return mav;
	}
	
	/* 기업 회원 : 챌린지 수정 */
	@Secured("ROLE_CORP")
	@PostMapping("/challenge/corp/challengeUpdate")
	public String challengeUpdate(ChallengeDto.ChallengeUpload challenge, Integer cno) {
		service.challengeUpdate(challenge, cno);
		return "redirect:/challenge/corp/challengeList";
	}
	
	/* 전체 유저 : 챌린지 목록 출력 */
	@GetMapping("/challenge/challengeList")
	public ModelAndView readchallengeList(Model model, Criteria cri, HttpServletRequest request, Principal principal) {
		
		if (request.isUserInRole("ROLE_MEMBER")) {			
			model.addAttribute("role", "ROLE_MEMBER");
		} else if(principal == null) {
			model.addAttribute("role", "null");
		} else {
			model.addAttribute("role", "ROLE_CORP");
		}
		return new ModelAndView().addObject("challenge", service.readchallengeList(cri)).addObject("pageMaker", new PageMakerDto(cri, service.getListTotal()));
	}
	
	/* 기업 회원 : 챌린지 목록 출력 */
	@Secured("ROLE_CORP")
	@GetMapping("/challenge/corp/challengeList")
	public ModelAndView readCorpChallengeList(Principal principal, Criteria cri) {
		return new ModelAndView("challenge/corp/challengeList").addObject("challenge", service.readCorpChallengeList(principal.getName(), cri)).addObject("pageMaker", new PageMakerDto(cri, service.getCorpListTotal(principal.getName())));
	}
	
	/* 전체 유저 : 챌린지 상세 페이지 출력 */
	@GetMapping("/challenge/member/challengeDetail")
	public ModelAndView readUserDetail(Integer cno, HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("login")==null) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		}
		
		if(request.isUserInRole("ROLE_MEMBER")) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno)).addObject("role", "ROLE_MEMBER");
		} else {
			return new ModelAndView("challenge/corp/challengeDetail").addObject("challenge", service.readUserDetail(cno)).addObject("role", "ROLE_CORP");
		}
	}
}