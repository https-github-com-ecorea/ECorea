package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

import javax.servlet.http.*;

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
	
	// 챌린지 인증 등록페이지
	@GetMapping("/challenge/member/challengeProveUpload")
	public void UploadProve(Integer cno) {
	}
	
	// 챌린지 인증 등록	
	@PostMapping("/challenge/member/challengeProveUpload")
	public String UploadProve(ChProveDto.InputProve dto) {	
		String memberId = "zzzzuny";
		proveService.UploadChProve(dto, memberId);
		return "redirect:/challenge/member/challengeList";
	}
	
	// 챌린지 인증 삭제
	@PostMapping("/challenge/member/deleteOne")
	public String deleteChProve(Integer cpno) {
		String memberId = "zzzzuny";
		proveService.deleteChProve(memberId, cpno);
		return "redirect:/challenge/member/challengeList";
	}
	
	// 일반회원 : 챌린지 신청 취소
	@PostMapping("/challenge/member/cancel")
	public String cancelJoin(Integer cno) {
		String memberId = "zzzzuny";
		proveService.cancelJoin(memberId, cno);
		return "redirect:/challenge/member/challengeList";
		/*
			챌린지 신청 취소하면 바뀌어야 할것들..?			
		 */
	}
	
}
