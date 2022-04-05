package com.project.ecorea.controller.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.QnaDto;
import com.project.ecorea.entity.QnaA;
import com.project.ecorea.entity.QnaQ;
import com.project.ecorea.service.QnaService;

import lombok.*;

@Controller
@AllArgsConstructor
public class QnaMvcController {
	
	@Autowired
	private QnaService service;
	
	/* 일반 회원 - 문의 목록 */
	@GetMapping("/mypage/member/qnaList")
	public ModelAndView memberQnaList(/*Principal principal*/String loginId) {
		return new ModelAndView("mypage/member/qnaList").addObject("memberQnaList", service.memberMyPageQuestionList("ngoley6"));
	}
	
	/* 일반 회원 - 문의 상세 */
	@GetMapping("/mypage/member/qnaDetail")
	public ModelAndView memberQnaDetail(String loginId, Integer qqno, String imagepath, HttpSession session) {
		session.setAttribute("memberId", loginId);
		return new ModelAndView("mypage/member/qnaDetail").addObject("memberQnaDetail", service.memberMypageDetail("ngoley6", qqno, imagepath));
	}
	
	/* 일반 회원 - 문의 등록 화면 */
	@GetMapping("/mypage/member/qnaUpload")
	public void uploadQnaQ() {
	}
	
	/* 일반 회원 - 문의 등록 */
	@PostMapping("/mypage/member/qnaUpload")
	public String uploadQnaQ(QnaDto.uploadQuestion questionUpDto, String loginId, Integer pno) {
		service.uploadQuestion(questionUpDto, "ngoley6", 10);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 수정 화면 */
	/*
	@GetMapping("/mypage/member/qnaUpdate")
	public ModelAndView updateQuestion() {
		String loginId = "ngoley6";
		Integer qqno = 3;
		return new ModelAndView().addObject("memberUpdateQuestion", service.memberMypageDetail(loginId, qqno, null));
	}
	*/
	
	/* 일반 회원 - 문의 수정 */
	@PostMapping("/mypage/member/qnaUpdate")
	public String updateQuestion(QnaQ questionDto) {
		service.updateQuestion(questionDto);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 일반 회원 - 문의 삭제 */
	@PostMapping("/mypage/member/qnaDelete")
	public String deleteQuestion(String loginId, Integer qqno, HttpSession session) {
		session.setAttribute("session", session);
		service.deleteQuestion("ngoley6", qqno);
		return "redirect:/mypage/member/qnaList";
	}
	
	/* 기업 회원 - 문의 목록 */
	@GetMapping("/mypage/corp/qnaList")
	public ModelAndView corpQnaList(/*Principal principal*/String loginId) {
		return new ModelAndView("mypage/corp/qnaList").addObject("corpQnaList", service.corpMyPageQuestionList("LG"));
	}

	/* 기업 회원 - 문의 상세 화면 */
	@GetMapping("/mypage/corp/qnaDetail")
	public ModelAndView corpQnaDetail(String loginId, Integer qqno, String imagepath) {
		return new ModelAndView("mypage/corp/qnaDetail").addObject("corpQnaDetail", service.corpMypageDetail(loginId, qqno, imagepath));
	}
	
	/* 기업 회원 - 문의 답변 등록 */
	@PostMapping("/mypage/corp/answerUpload")
	public String uploadQnaA(QnaDto.AnswerDto answerUpDto, Integer pno, String loginId) {
		service.uploadAnswer(answerUpDto, pno, "LG");
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 수정 */
	@PostMapping("/mypage/corp/answerUpdate")
	public String updateAnswer(QnaA answerDto) {
		service.updateAnswer(answerDto);
		return "redirect:/mypage/corp/qnaList";
	}
	
	/* 기업 회원 - 문의 답변 삭제 */
	@PostMapping("/mypage/corp/answerDelete")
	public String deleteAnswer(String loginId, Integer qano) {
		service.deleteAnswer(loginId, qano);
		return "redirect:/mypage/corp/qnaList";
	}
	
}
