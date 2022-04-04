package com.project.ecorea.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ChallengeService service;

	/* 기업 회원 : 챌린지 등록 화면 */
	@GetMapping("/challenge/corp/challengeUpload")
	public void challengeUpload() {
	}
	
	/* 기업 회원 : 챌린지 등록 */
	@PostMapping("/challenge/corp/challengeUpload")
	public String challengeUpload(ChallengeDto.challengeUpload challenge) {
		service.challengeUpload(challenge);
		return "rediret:/challenge/challengeList";
	}
	
	/* 기업 회원 : 챌린지 수정 화면 */
	@GetMapping("/challenge/corp/challengeUpdate")
	public ModelAndView challengeUpdate() {
		return new ModelAndView("challenge/corp/challengeUpdate");
	}
	
	/* 기업 회원 : 챌린지 수정 */
	@PostMapping("/challenge/corp/challengeUpdate")
	public String challengeUpdate(Challenge challenge) {
		service.challengeUpdate(challenge);
		return "rediret:/challenge/challengeList";
	}
	
}
