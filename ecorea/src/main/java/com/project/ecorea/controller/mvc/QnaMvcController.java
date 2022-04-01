package com.project.ecorea.controller.mvc;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class QnaMvcController {
	
	/* 일반 회원 - 문의 목록 */
	@GetMapping("/mypage/member/qnaList")
	public ModelAndView memberQnaList(@RequestParam(defaultValue="1") Integer pageno) {
		return new ModelAndView("mypage/member/qnaList");
	}
	
	/* 일반 회원 - 문의 상세 */
	@GetMapping("/mypage/member/qnaDetail")
	public ModelAndView memberQnaDetail(Integer qno) {
		return new ModelAndView("mypage/member/qnaDetail");
	}
	
	/* 일반 회원 - 문의 등록 화면 */
	@GetMapping("/mypage/member/qnaUpload")
	public void uploadQnaQ() {
	}
	
	/* 일반 회원 - 문의 등록 */
	@PostMapping("/mypage/member/qnaUpload")
	public String uploadQnaQ(String dto) {
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 기업 회원 - 문의 목록 */
	@GetMapping("/mypage/corp/qnaList")
	public ModelAndView corpQnaList(@RequestParam(defaultValue="1") Integer pageno) {
		return new ModelAndView("mypage/corp/qnaList");
	}

	/* 기업 회원 - 문의 상세 화면 */
	@GetMapping("/mypage/corp/qnaDetail")
	public ModelAndView corpQnaDetail(Integer qno) {
		return new ModelAndView("mypage/corp/qnaDetail");
	}
	
	/* 기업 회원 - 문의 답변 등록 */
	@PostMapping("/mypage/corp/qnaDetail")
	public String uploadQnaA(String dto) {
		return "redirect:/mypage/corp/qnaList";
	}

}
