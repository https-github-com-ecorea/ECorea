package com.project.ecorea.controller.mvc;

import java.security.*;

import javax.servlet.http.*;

import org.springframework.security.access.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

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
	public ModelAndView readchallengeList(Criteria cri) {
		return new ModelAndView().addObject("challenge", service.readchallengeList(cri)).addObject("pageMaker", new PageMakerDto(cri, service.getListTotal()));
	}
	
	/* 기업 회원 : 챌린지 목록 출력 */
	@Secured("ROLE_CORP")
	@GetMapping("/challenge/corp/challengeList")
	public ModelAndView readCorpChallengeList(Principal principal, Criteria cri) {
		return new ModelAndView("challenge/corp/challengeList").addObject("challenge", service.readCorpChallengeList(principal.getName(), cri))
				.addObject("pageMaker", new PageMakerDto(cri, service.getCorpListTotal(principal.getName())));
	}
	
	/* 전체 유저 : 챌린지 상세 페이지 출력 */
	@GetMapping("/challenge/member/challengeDetail")
	public ModelAndView readUserDetail(Integer cno, Principal principal, HttpServletRequest request) {
		// 로그인하지 않은 회원이 접근했을때
		if(principal==null) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		}
		
		// MEMBER 권한을 가진 회원이 접속했을 때 권한을 HTML로 보내 비회원인지 로그인한 회원인지를 구분해 챌린지 신청버튼의 기능이 달라진다
		if(request.isUserInRole("ROLE_MEMBER")) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno)).addObject("role", "ROLE_MEMBER");
		} else {
			// CORP 권한을 가진 회원이 접속했을 때
			return new ModelAndView("challenge/corp/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		}
	}
}